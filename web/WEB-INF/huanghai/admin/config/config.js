/**
 * Created by admin on 2017/11/13.
 */
var Config = {
    isBug: true,
    loginSkipPage: "userManage.html",
    timeout: 600000,
    sidebar: {
        "1": {
            "name": "用户管理",
            "intro": "新增、删除用户，修改用户信息",
            "icon": "fa-coffee",
            "url": "userManage.html"
        },
        "2": {
            "url": "roleManage.html",
            "icon": "fa-coffee",
            "name": "角色管理",
            "intro": "创建、删除角色，以及对角色的权限进行修改"
        },
        "3": {
            "url": "dataInsert.html",
            "icon": "fa-coffee",
            "name": "数据录入"
        },
        "4": {
            "url": "dataManage.html",
            "icon": "fa-coffee",
            "name": "数据管理"
        },
        "5": {
            "url": "systemManage.html",
            "icon": "fa-coffee",
            "name": "系统管理"
        },
        "6": {
            "url": "examineManage.html",
            "icon": "fa-coffee",
            "name": "审核管理"
        },
        "7": {
            "url": "message.html",
            "icon": "fa-coffee",
            "name": "消息中心"
        }
    }
};

var Service = {
    getUrl: function (type) {
        if (Config.isBug) {
            switch (type) {
                case "login":
                    return "/login";
                case "createUser":
                    return "/register";
                case "modifyUser":
                    return "/modify";
                case "deleteUser":
                    return "/deleteUser";
                case "getUser":
                    return "/getUserList";
                case "createRole":
                    return "/createRole";
                case "modifyRole":
                    return "/modifyRole";
                case "deleteRole":
                    return "/deleteRole";
                case "getRole":
                    return "/getRoleList";
                case "getRoleSelect":
                    return "/getRoleSelect";
                case "getParam":
                    return "/getTypeAndEC2";
                case "createData":
                    return "/manualInput";
                case "modifyData":
                    return "/modifyInput";
                case "deleteData":
                    return "/deleteData";
                case "getDataList":
                    return "/getData";
                case 99:
                    return "data/successTest";

            }
        }
    }
};