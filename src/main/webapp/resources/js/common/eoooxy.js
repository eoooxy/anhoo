/**
 * js工具包
 * @Author: eoooxy@163.com
 * @Version: 0.1 beta
 * @Since: 2017-04-26
 * @LastUpdate: 2017-04-26
 */

var eoooxy = {version: "0.1 beta"};

/**
 * 去掉二边多余空格
 * @param str
 * @returns {string}
 */
eoooxy.trim = function (str) {
    return str.replace(/(^[\s]*)|([\s]*$)/g, "");
};

/**
 * 去掉左边多余空格
 * @param str
 * @returns {string}
 */
eoooxy.leftTrim = function (str) {
    return str.replace(/(^[\s]*)/g, "");
};

/**
 * 去掉右侧多余空格
 * @param str
 */
eoooxy.rightTrim = function (str) {
    return str.replace(/([\s]*$)/g, "");
};

/**
 * 字符串去二边空格并转大写
 */
eoooxy.trimAndUpper = function (str) {
    return eoooxy.trim(str).toUpperCase();
};

/**
 * 判断字符串是否为空
 * @param str
 * @returns {boolean}
 */
eoooxy.isEmpty = function (str, isTrim) {
    if (isTrim != undefined && isTrim) {
        str = eoooxy.trim(str);
    }
    return str == null || !str || typeof str == undefined || str == '';
};

/**
 * 判断两个字符串是否相等  相等返回true 否则返回false
 * @param str1
 * @param str2
 * @returns {boolean}
 */
eoooxy.isEqual = function (str1, str2) {
    return str1 == str2;
};

/**
 * @param type  ajax请求类型 post／get
 * @param url  请求地址
 * @param data  请求参数
 * @param datatype
 * @param successCallback 成功调用的函数
 * @param errorCallback  失败调用的函数
 */
eoooxy.ajax = function (type, url, data, successCallback, datatype, errorCallback, async) {
    $.ajax({
        type: type,
        async: (async == null || async == undefined) ? true : async,
        url: url,
        data: data,
        success: successCallback,
        dataType: datatype,
        error: errorCallback
    });
}
