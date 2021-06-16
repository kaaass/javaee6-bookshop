/**
 * 商品插件
 */
require([
    'jquery',
    'module/functions',
    'module/constants',
    'module/auth',
    'module/admin',
    'module/product',
    'bootstrap'], function ($, functions, constants, auth, admin, product, _) {

    const TEMPLATE_LIST = 'product_list';

    let $list = $('.table-responsive'),
        $add = $('#btn-add'),
        $checkIsbn = $('#btn-check'),
        // Form
        $name = $('#name'),
        $thumbnailId = $('#thumbnailId'),
        $price = $('#price'),
        $mailPrice = $('#mailPrice'),
        $buyLimit = $('#buyLimit'),
        $categoryId = $('#categoryId'),
        $startSellTime = $('#startSellTime'),
        $rest = $('#rest');

    let loadParam = (product) => {
        $('#name').val(product ? product.name : "");
        $('#thumbnailId').val(product ? product.thumbnail.id : "");
        $('#price').val(product ? product.price : "");
        $('#mailPrice').val(product ? product.mailPrice : "");
        $('#buyLimit').val(product ? product.buyLimit : "");
        $('#categoryId').val(product ? product.category.id : "");
        $('#startSellTime').val(product ? product.startSellTimeReadable : "");
        $('#rest').val(product ? product.storage.rest : "");
        $('#author').val(product ? product.author : "");
        $('#isbn').val(product ? product.isbn : "");
        $('#publishDate').val(product ? product.publishDateReadable : "");
        $('#indexOrder').val(product ? product.indexOrder : "");
    };

    let getParam = () => {
        // TODO 检查是否为空
        return {
            name: $name.val(),
            thumbnailId: $thumbnailId.val(),
            price: $price.val(),
            mailPrice: $mailPrice.val(),
            buyLimit: $buyLimit.val(),
            categoryId: $categoryId.val(),
            startSellTime: functions.dateToTs($startSellTime.val()),
            rest: $rest.val(),
            author: $('#author').val(),
            isbn: $('#isbn').val(),
            publishDate: functions.dateToTs($('#publishDate').val()),
            indexOrder: $('#indexOrder').val()
        };
    };

    // 加载图标
    feather.replace();

    // 渲染插件列表
    let render = async () => {
        await product.renderProductsByUrl('/product/', $list, TEMPLATE_LIST);
        // 编辑
        $('.btn-edit').click(function () {
            let id = $(this).attr('product-id');
            loadParam(product.productCache[id]);
            $('#productModal').modal('show');
            // 添加
            $add.unbind('click');
            $add.click(() => {
                let param = getParam();
                product.editProduct(id, param)
                    .then(result => {
                        if (result)
                            functions.modal("信息", "编辑商品成功！");
                        render();
                    });
            });
        });
        // 移除
        $('.btn-remove').click(function () {
            let id = $(this).attr('product-id');
            product.removeProduct(id)
                .then(result => {
                    if (result)
                        functions.modal("信息", "删除商品成功！");
                    render();
                });
        });
        // 关联订单
        $('.btn-order').click(function () {
            let id = $(this).attr('product-id');
            functions.jumpTo(`orders-product.html?id=${id}`);
        });
    };
    render();

    // TODO 增加元数据相关

    // 事件绑定
    $('#btn-create').click(() => {
        $('#productModal').modal('show');
        loadParam(null);
        // 添加
        $add.unbind('click');
        $add.click(() => {
            let param = getParam();
            product.addProduct(param)
                .then(result => {
                    if (result)
                        functions.modal("信息", "添加商品成功！");
                    render();
                });
        });
    });

    // 检查 ISBN
    $checkIsbn.unbind('click');
    $checkIsbn.click(() => {
        let isbn = $('#isbn').val();
        product.checkIsbn(isbn)
            .then(result => {
                if (result)
                    alert("格式正确！格式化：" + result);
            });
    });
});