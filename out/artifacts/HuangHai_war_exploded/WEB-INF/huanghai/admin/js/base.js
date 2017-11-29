/**
 * Created by admin on 2017/11/17.
 */
function init(client, fn) {
    var isLogin = sessionStorage.getItem("isLogin"),
        user = null,
        auth = null,
        auths = [],
        url = location.href,
        urlSplit = url.split("/"),
        page = urlSplit[urlSplit.length - 1],

        length = 0,
        loop = 0,
        sidebarLinks = [],
        item = null,
        flag = false,

        timer = null;

    // 判断当前用户是否登录
    if(!isLogin) {
        Dialog.custom(
            "提示",
            "抱歉，您还没有登录，请登录后再进入该页面",
            function() {
                location.href = "signin.html";
            }
        )
    } else {
        user = JSON.parse(sessionStorage.getItem("user"));
        auth = user.roleAuth;
        auths = auth.split("|");
        // 根据权限显示相应的操作内容，并判断用户是否用权限进入当前页面
        if(page === "profile.html" || page === "help.html") {
            flag = true;
        }
        for(loop = 0, length = auths.length; loop < length; loop++) {
            item = Config.sidebar[auths[loop]];
            sidebarLinks.push(item);
            if(item.url === page) {
                flag = true;
            }
        }
        if(!flag) {
            Dialog.custom(
                "提示",
                "抱歉，您还没有权限访问该页面",
                function() {
                    history.back();
                }
            )
        } else {
            Client.sidebarLinks = sidebarLinks;

            Client.user = JSON.parse(sessionStorage.getItem("user"));
            fn();
        }
    }

    // 超时处理检测
    timer = setTimeout(timeout, Config.timeout);
    function timeout() {
        clearTimeout(timer);
        sessionStorage.removeItem("isLogin");
        location.href = "lock.html";
    }
    function resetTime() {
        clearTimeout(timer);
        timer = setTimeout(timeout, Config.timeout);
    }
    $(document).on("keydown", resetTime).on("mouseover", resetTime);
}
function successCallback(msg) {
    if(Number(msg.code) === 1) {
        Dialog.info(msg.msg);
    } else {
        Dialog.warn(msg.msg);
    }
}
function errorCallback(msg) {
    Dialog.warn("连接服务器失败！");
}
function getDate() {
    var date = new Date(),
        year = date.getFullYear(),
        month = date.getMonth() + 1,
        day = date.getDate(),
        hours = date.getHours(),
        minutes = date.getMinutes(),
        seconds = date.getSeconds();

    if(month < 0) {
        month = "0" + month;
    }
    if(day < 0) {
        day = "0" + day;
    }
    if(hours < 0) {
        hours = "0" + hours;
    }
    if(minutes < 0) {
        minutes = "0" + minutes;
    }
    if(seconds < 0) {
        seconds = "0" + seconds;
    }
    return year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
}
// 连接
var Links = {
    methods: {},
    connect: function (data) {
        var method = data.method;
        try {
            if (!method) {
                throw new Error("");
            }
            Links.methods[method].run(data.param);
        } catch (error) {
            console.warn(error);
        }
    }
};
Links.methods.get = {
    run: function (param) {
        $.ajax({
            type: "GET",
            url: param.url,
            data: param.data,
            success: param.successFn,
            error: param.errorFn
        });
    }
};
Links.methods.post = {
    run: function (param) {
        $.ajax({
            type: "POST",
            url: param.url,
            data: param.data,
            success: param.successFn,
            error: param.errorFn
        });
    }
};
// 弹窗
var Dialog = {
    logout : function() {
        easyDialog.open({
            container : {
                header : '注销用户',
                content : '确定要注销当前用户吗？',
                yesFn : function(){ location.href = "index.html";},
                noFn : true
            }
        });
    },
    warn : function(msg) {
        easyDialog.open({
            container : {
                header : '错误警告',
                content : msg,
                noFn : true,
                noText : '了解'
            }
        })
    },
    tip : function(msg) {
        easyDialog.open({
            container : ({
                header : '提示',
                content : msg,
                noFn : true,
                noText : '了解'
            })
        })
    },
    info : function(msg) {
        easyDialog.open({
            container : ({
                header : '消息',
                content : msg,
                noFn : true,
                noText : '确定'
            })
        })
    },
    custom : function(header, content, yesFn, noFn, yesText, noText) {
        easyDialog.open({
            container : ({
                header : header,
                content : content,
                yesFn : yesFn,
                noFn : noFn,
                yesText : yesText,
                noText : noText
            })
        })
    }
};
// 验证器
var Validator = function() {
    this.account = function(account) {
        return /^\w{6,12}$/.exec(account);
    };
    this.password = function(password) {
        return password.length >=6 && password.length <= 15 ? true : false;
    };
    this.pages = function(pages, maxPages) {
        pages = Number(pages);
        return !(!/[0-9]+/.test(pages) || pages < 1 || pages > maxPages);
    };
    this.pageNumber = function(number) {
        number = Number(number);
        return !(!/[0-9]+/.test(number) || number < 1);
    };
};