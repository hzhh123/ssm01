/**
 * Created by Administrator on 2017/6/18.
 */
/**
 * 主要用于序列化单选和复选未选择时不会传递值的情况
 * @returns {{}}
 */
$.fn.serializeObject = function () {
    var a = this.serializeArray();
    var $radio = $('input[type=radio],input[type=checkbox]', this);
    var temp = {};
    $.each($radio, function () {
        if (!temp.hasOwnProperty(this.name)) {
            if ($("input[name='" + this.name + "']:checked").length == 0) {
                temp[this.name] = "";
                a.push({name: this.name, value: ""});
            }
        }
    });
    //console.log(a);
    return jQuery.param(a);
};