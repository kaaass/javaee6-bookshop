package net.kaaass.bookshop.promote.strategy.dbms;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import net.kaaass.bookshop.dto.PromoteStrategyDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.promote.BaseDbmsPromoteStrategy;
import net.kaaass.bookshop.promote.OrderPromoteContext;
import net.kaaass.bookshop.promote.ServiceAdapter;
import net.kaaass.bookshop.script.ScriptSource;
import net.kaaass.bookshop.script.StandardScriptEvaluator;
import net.kaaass.bookshop.util.Constants;
import net.kaaass.bookshop.util.FileUtils;

import java.io.IOException;
import java.util.HashMap;

/**
 * 脚本策略
 */
@Slf4j
public class JavascriptStrategy extends BaseDbmsPromoteStrategy<OrderPromoteContext, OrderPromoteContext> {

    /**
     * 参数
     */
    @Data
    public static class Param {

        /**
         * 脚本路径
         */
        private String javascriptFile;
    }

    private Param param = null;

    private String function = null;

    @Override
    protected void initialize(PromoteStrategyDto promoteStrategyDto, ServiceAdapter serviceAdapter) throws BaseException {
        val paramStr = promoteStrategyDto.getParam();
        this.param = parseJsonParam(paramStr, Param.class);
        // 读取脚本内容
        try {
            function = FileUtils.readAll(this.param.javascriptFile);
        } catch (IOException e) {
            log.warn("脚本文件读入错误：", e);
            throw new BadRequestException("脚本文件读入错误！");
        }
    }

    @Override
    public Result<OrderPromoteContext> doPromote(OrderPromoteContext context) {
        StandardScriptEvaluator evaluator = new StandardScriptEvaluator();
        evaluator.setLanguage(Constants.SCRIPT_TYPE_JAVASCRIPT);
        val arguments = new HashMap<String, Object>();
        // 触发事件获得附加参数
        arguments.put("promoteContext", context);
        arguments.put("extraInfo", getPromoteInfo());
        arguments.put("OK", ResultType.OK);
        arguments.put("NOT_COND", ResultType.NOT_COND);
        arguments.put("NOT_MATCH", ResultType.NOT_MATCH);
        try {
            arguments.put("result_mtd", Result.class.getDeclaredMethod("of", ResultType.class, OrderPromoteContext.class));
        } catch (NoSuchMethodException e) {
            log.warn("无法获得 Result::of 方法", e);
        }
        // 执行脚本
        Object result = null;
        try {
            result = evaluator.evaluate(new ScriptSource(function), arguments);
        } catch (Exception e) {
            log.warn("执行脚本错误：", e);
        }
        if (result == null) {
            return new Result<>(ResultType.NOT_MATCH);
        }
        log.info("脚本执行成功 {}", result);
        return (Result<OrderPromoteContext>) result;
    }
}
