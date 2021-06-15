function make_result(st, ctx) { return result_mtd.invoke(null, st, ctx); };

var result = null;

println(OK);
println(OK.class);
println(promoteContext);
println(promoteContext.class);

// 总价格满 100 参与折扣
if (promoteContext.price > 100) {
    // 商品打 8 折，保留两位小数
    var discount = promoteContext.price * 0.2;
    discount.toFixed(2);
    // 最高减 50
    if (discount > 50) {
        discount = 50;
    }
    promoteContext.price -= discount;
    result = make_result(OK, promoteContext);
} else {
    result = make_result(NOT_COND, promoteContext);
}

// 返回打折结果
result;
