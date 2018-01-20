/**
 * Created by admin on 2018/1/17.
 */
/* 顶部 */
var templateHeader =
    '<header class="header header-fixed navbar" style="box-shadow: 3px 0 5px 2px #535a6c;">' +
    '<div class="brand">' +
    '<a href="javascript:;" class="fa fa-bars off-left visible-xs" data-toggle="off-canvas" data-move="ltr"></a>' +
    '<a href="profile.html" class="navbar-brand">' +
    '<i class="fa fa-stop mg-r-sm"></i>' +
    '<span class="heading-font">后台管理平台</span>' +
    '</a>' +
    '</div>' +
    '<ul class="nav navbar-nav navbar-right off-right">' +
    '<li class="quickmenu">' +
    '<a href="javascript:;" data-toggle="dropdown">' +
    '<img src="../asset/img/admin/avatar.png" class="avatar pull-left img-circle" alt="user" title="user">' +
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
    template: templateHeader,
    props: ["user", "message"]
};

/* 菜单栏 */
var templateSidebar =
    '<aside class="sidebar canvas-left">' +
    '<nav class="main-navigation">' +
    '<ul>' +
    '<li v-for="item in links">' +
    '<a :href="item.url">' +
    '<i class="fa" :class="item.icon"></i>' +
    '<span v-text="item.name"></span>' +
    '</a>' +
    '</li>' +
    '</ul>' +
    '</nav>' +
    '<footer>' +
    '<div class="footer-toolbar pull-left">' +
    '<a href="help.html" class="pull-left help">' +
    '<i class="fa fa-question-circle"></i>' +
    '</a>' +
    '<a href="javascript:;" @click="shrink()" class="toggle-sidebar pull-right hidden-xs">' +
    '<i class="fa fa-angle-left"></i>' +
    '</a>' +
    '</div>' +
    '</footer>' +
    '</aside>';

var sidebar = {
    template: templateSidebar,
    props: ["links"],
    methods: {
        shrink: function() {
            $("#client").toggleClass("small-sidebar");
        }
    }
};