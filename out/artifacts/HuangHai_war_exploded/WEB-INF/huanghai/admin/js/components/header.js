/**
 * Created by admin on 2017/11/16.
 */
let template =
    '<header class="header header-fixed navbar">' +
        '<div class="brand">' +
            '<a href="javascript:;" class="fa fa-bars off-left visible-xs" data-toggle="off-canvas" data-move="ltr"></a>' +
            '<a href="index.html" class="navbar-brand">' +
                '<i class="fa fa-stop mg-r-sm"></i>' +
                '<span class="heading-font">后台管理系统</span>' +
            '</a>' +
        '</div>' +
        '<ul class="nav navbar-nav navbar-right off-right">' +
            '<li class="notifications dropdown hidden-xs">' +
                '<a href="javascript:;" data-toggle="dropdown">' +
                    '<i class="fa fa-bell"></i>' +
                    '<div class="badge badge-top bg-danger animated flash" v-if="message.length > 0"><span v-text="message.length"></span></div>' +
                '</a>' +
                '<div class="dropdown-menu dropdown-menu-right animated slideInRight">' +
                    '<div class="panel bg-white no-border no-margin">' +
                        '<div class="panel-heading no-radius">' +
                            '<small><b>通知</b></small>' +
                            '<small class="pull-right">' +
                                '<a href="javascript:;" class="mg-r-xs">标记为已读</a>&#8226;' +
                                '<a href="setting.html" class="fa fa-cog mg-l-xs"></a>' +
                            '</small>' +
                        '</div>' +
                        '<ul class="list-group">' +
                            '<li v-if="message.length > 0" class="list-group-item" v-for="item in message">' +
                                '<a href="javascript:;">' +
                                    '<span class="pull-left mg-t-xs mg-r-md" v-text="item.username"></span>' +
                                    '<div class="m-body show pd-t-xs">' +
                                        '<span v-text="item.operation"></span>' +
                                    '</div>' +
                                '</a>' +
                            '</li>' +
                            '<li v-else class="list-group-item">暂无最新消息</li>' +
                        '</ul>' +
                        '<div class="panel-footer no-border">' +
                            '<a href="javascript:;">浏览所有通知</a>' +
                        '</div>' +
                    '</div>' +
                '</div>' +
            '</li>' +
            '<li class="quickmenu">' +
                '<a href="javascript:;" data-toggle="dropdown">' +
                    '<img src="img/avatar.jpg" class="avatar pull-left img-circle" alt="user" title="user">' +
                    '<i class="caret mg-l-xs hidden-xs no-margin"></i>' +
                '</a>' +
                '<ul class="dropdown-menu dropdown-menu-right mg-r-xs">' +
                    '<li><a href="profile.html"><div class="pd-t-sm" v-text="user.userName"></div></a></li>' +
                    '<li class="divider"></li>' +
                    '<li><a href="setting.html">系统配置</a></li>' +
                    '<li><a href="message.html">消息通知<div class="badge bg-danger pull-right">3</div></a></li>' +
                    '<li><a href="help.html">查看帮助</a></li>' +
                    '<li class="divider"></li>' +
                    '<li><a href="signin.html">退出账户</a></li>' +
                '</ul>' +
            '</li>' +
        '</ul>' +
    '</header>';

let header = {
    template: template,
    props: ["user", "message"]
};