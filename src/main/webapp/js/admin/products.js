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
    const TEMPLATE_CACHE_LIST = 'product_cache_list';

    let $list = $('.table-responsive'),
        $cacheList = $('.table-cache'),
        $add = $('#btn-add'),
        $checkIsbn = $('#btn-check'),
        // Form
        $name = $('#name'),
        $thumbnailId = $('#thumbnailId'),
        $price = $('#price'),
        $mailPrice = $('#mailPrice'),
        $categoryId = $('#categoryId'),
        $rest = $('#rest');

    let loadParam = (product) => {
        $('#name').val(product ? product.name : "");
        $('#thumbnailId').val(product && product.thumbnail ? product.thumbnail : "");
        $('#price').val(product ? product.price : "");
        $('#categoryId').val(product && product.category ? product.category.id : "");
        $('#rest').val(product && product.storage ? product.storage.rest : "");
        $('#author').val(product ? product.author : "");
        $('#isbn').val(product ? product.isbn : "");
        $('#publishDate').val(product ? product.publishDateReadable : "");
    };

    let getParam = () => {
        // TODO 检查是否为空
        return {
            name: $name.val(),
            thumbnailId: $thumbnailId.val(),
            price: $price.val(),
            categoryId: $categoryId.val(),
            rest: $rest.val(),
            author: $('#author').val(),
            isbn: $('#isbn').val(),
            publishDate: functions.dateToTs($('#publishDate').val()),
        };
    };



    // 渲染商品列表
    let render = async () => {
        await product.renderProductsByUrl('/product/', $list, TEMPLATE_LIST, false, true);
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
        // 元数据
        $('.btn-meta').click(function () {
            let id = $(this).attr('product-id');
            functions.jumpTo(`products-metadata.html?id=${id}`);
        });
    };
    render();

    // 渲染商品缓冲列表
    let renderCache = async () => {
        await product.renderProductsByUrl('/product/cache/', $cacheList, TEMPLATE_CACHE_LIST, false, true);
        // 编辑
        $('.btn-cache-edit').click(function () {
            let id = $(this).attr('product-id');
            loadParam(product.productCache[id]);
            $('#productModal').modal('show');
            // 添加
            $add.unbind('click');
            $add.click(() => {
                let param = getParam();
                product.editProductCache(id, param)
                    .then(result => {
                        if (result)
                            functions.modal("信息", "编辑商品缓冲成功！");
                        renderCache();
                    });
            });
        });
        // 移除
        $('.btn-cache-remove').click(function () {
            let id = $(this).attr('product-id');
            product.removeProductCache(id)
                .then(result => {
                    if (result)
                        functions.modal("信息", "删除商品缓冲成功！");
                    renderCache();
                });
        });
    };
    renderCache();

    // 事件绑定
    let createCache = null;

    $('#btn-create').click(() => {
        $('#productModal').modal('show');
        loadParam({
            ...createCache,
            thumbnail: {
                id: createCache ? createCache.thumbnailId : ""
            },
            category: {
                id: createCache ? createCache.categoryId : ""
            },
            storage: {
                rest: createCache ? createCache.rest : ""
            },
            publishDateReadable: createCache ? functions.dateFormatTs(createCache.publishDate) : "",
        });
        // 添加商品
        $add.unbind('click');
        $add.click(() => {
            let param = getParam();
            createCache = null;
            product.addProductCache(param)
                .then(result => {
                    if (result)
                        functions.modal("信息", "添加商品成功！");
                    renderCache();
                })
                .catch(e => {
                    // 失败则记录表单内容
                    createCache = param;
                });
        });
    });

    $('#btn-submit').click(() => {
        // 提交商品
        product.commitProductCache()
            .then(result => {
                if (result)
                    functions.modal("信息", "提交商品成功！");
                renderCache();
                render();
            });
    });

    $('#btn-clear').click(() => {
        // 清空商品缓存
        product.clearProductCache()
            .then(result => {
                if (result)
                    functions.modal("信息", "清空缓存成功！");
                renderCache();
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