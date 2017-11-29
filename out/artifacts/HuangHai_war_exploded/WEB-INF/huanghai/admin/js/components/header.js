/**
 * Created by admin on 2017/11/16.
 */
var template =
    '<header class="header header-fixed navbar">' +
        '<div class="brand">' +
            '<a href="javascript:;" class="fa fa-bars off-left visible-xs" data-toggle="off-canvas" data-move="ltr"></a>' +
            '<a href="index.html" class="navbar-brand">' +
                '<i class="fa fa-stop mg-r-sm"></i>' +
                '<span class="heading-font">后台管理系统</span>' +
            '</a>' +
        '</div>' +
        '<ul class="nav navbar-nav navbar-right off-right">' +
            '<li class="quickmenu">' +
                '<a href="javascript:;" data-toggle="dropdown">' +
                    '<img src="img/avatar.jpg" class="avatar pull-left img-circle" alt="user" title="user">' +
                    '<i class="caret mg-l-xs hidden-xs no-margin"></i>' +
                '</a>' +
                '<ul class="dropdown-menu dropdown-menu-right mg-r-xs">' +
                    '<li><a href="profile.html"><div class="pd-t-sm" v-text="user.userName"></div></a></li>' +
                    '<li class="divider"></li>' +
                    '<li><a href="system.html">系统配置</a></li>' +
                    '<li><a href="help.html">查看帮助</a></li>' +
                    '<li class="divider"></li>' +
                    '<li><a href="signin.html">退出账户</a></li>' +
                '</ul>' +
            '</li>' +
        '</ul>' +
    '</header>';

var header = {
    template: template,
    props: ["user", "message"]
};