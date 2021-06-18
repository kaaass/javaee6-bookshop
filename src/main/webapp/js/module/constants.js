/**
 * 常量声明
 */
define([], function () {

    const API_BASE_URL = window.BASE_URL + "/api";

    return {
        KEY_AUTH: 'AUTH',
        KEY_NAME: 'NAME',
        KEY_ADMIN_AUTH: 'ADMIN_AUTH',

        PARAM_ID: 'id',
        PARAM_TYPE: 'type',
        PARAM_CART_IDS: 'cartIds',

        TEMPLATE_PATH: BASE_URL + '/templates/',
        TEMPLATE_SUFFIX: '.hbs',

        TITLE_SUFFIX: " - 图书商城",

        orderRequestType: {
            SINGLE: 'SINGLE',
            MULTI: 'MULTI'
        },

        BASE_URL: BASE_URL,
        API_BASE_URL: API_BASE_URL,

        TIME_AREA: +8,
    };
});