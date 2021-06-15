package net.kaaass.bookshop.promote;

import lombok.val;
import lombok.var;
import net.kaaass.bookshop.dto.PromoteStrategyDto;
import net.kaaass.bookshop.exception.BadRequestException;
import net.kaaass.bookshop.exception.BaseException;
import net.kaaass.bookshop.vo.PromoteStrategyInfoVo;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

/**
 * Dbms销售策略基类
 * @param <S>
 * @param <T>
 */
public abstract class BaseDbmsPromoteStrategy<S extends OrderPromoteContext, T extends OrderPromoteContext>
        implements IPromoteStrategy<S, T> {

    PromoteStrategyInfoVo promoteStrategyInfoVo;

    public BaseDbmsPromoteStrategy() {}

    protected abstract void initialize(PromoteStrategyDto promoteStrategyDto, ServiceAdapter serviceAdapter) throws BaseException;

    @Override
    public PromoteStrategyInfoVo getPromoteInfo() {
        return this.promoteStrategyInfoVo;
    }

    protected static <T> T parseJsonParam(String jsonStr, Class<T> clazz) throws BadRequestException {
        val mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (IOException e) {
            throw new BadRequestException("策略参数解析错误", e);
        }
    }
}
