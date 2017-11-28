/**
 * Created by admin on 2017/11/13.
 */
var Config = {
    isBug: false,
    loginSkipPage: "profile.html",
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
            "url": "system.html",
            "icon": "fa-coffee",
            "name": "系统管理"
        },
        "6": {
            "url": "examine.html",
            "icon": "fa-coffee",
            "name": "审核管理"
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
                case "modifyPwd":
                    return "/changePassword";
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
        } else {
            switch (type) {
                case "login":
                    return "/login";
                case "createUser":
                    return "/register";
                case "modifyUser":
                    return "/modify";
                case "modifyPwd":
                    return "/changePassword";
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
                    // 数据录入：创建数据
                    return "/manualInput";
                case "getDataList":
                    // 数据管理：获取数据列表
                    return "/getData";
                case "modifyData":
                    // 数据管理：修改数据
                    return "/modifyInput";
                case "deleteData":
                    return "/deleteData";
                case "getPageConfig":
                    // 系统管理：获取页面配置信息
                    return "/getConfigList";
                case "modifyPage":
                    // 系统管理：修改页面配置信息
                    return "/modifyConfig";
                case "getDataConfig":
                    // 系统管理：获取数据配置信息
                    return "/getDataConfigList";
                case "getTypeList":
                    return "/getType";
                case "addType":
                    // 系统管理：添加分类
                    return "/addType";
                case "deleteType":
                    // 系统管理：删除分类
                    return "/deleteType";
                case "addEC1":
                    // 系统管理：新增酶大类
                    return "/addEC1";
                case "deleteEC1":
                    // 系统管理：删除酶大类
                    return "/deleteEC1";
                case "getEC2List":
                    // 系统管理：获取酶种列表
                    return "/getEC2";
                case "addEC2":
                    // 系统管理：新增酶种
                    return "/addEC2";
                case "deleteEC2":
                    // 系统管理：删除酶种
                    return "/deleteEC2";
                case 99:
                    return "data/successTest";

            }
        }
    }
};