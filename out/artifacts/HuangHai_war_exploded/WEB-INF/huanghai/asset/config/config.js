/**
 * Created by admin on 2017/11/13.
 */
var Config = {
    loginSkipPage: "profile.html",
    timeout: 600000,
    sidebar: {
        "1": {
            "name": "用户管理",
            "intro": "新增、删除用户，以及修改用户信息",
            "icon": "fa-user",
            "url": "userManage.html"
        },
        "2": {
            "url": "roleManage.html",
            "icon": "fa-sitemap",
            "name": "角色管理",
            "intro": "创建、删除角色，以及对角色的权限进行修改"
        },
        "3": {
            "url": "dataInsert.html",
            "icon": "fa-plus-square",
            "name": "数据录入",
            "intro": "手工录入数据或批量导入EXCEL数据文件"
        },
        "4": {
            "url": "dataManage.html",
            "icon": "fa-file-text",
            "name": "数据管理",
            "intro": "提交待审核数据"
        },
        "5": {
            "url": "system.html",
            "icon": "fa-cogs",
            "name": "系统管理",
            "intro": "管理页面配置、数据配置以及爬虫控制"
        },
        "6": {
            "url": "examine.html",
            "icon": "fa-list-alt",
            "name": "审核管理",
            "intro": "通过或拒绝角色待审核数据"
        }
    }
};

var Service = {
    getUrl: function (type) {
        switch (type) {
            case "login":
                // 登录界面：用户登录
                return "/login";
            case "createUser":
                // 用户管理：创建用户
                return "/register";
            case "modifyUser":
                // 用户管理：修改用户
                return "/modify";
            case "modifyPwd":
                // 用户管理：修改用户密码
                return "/changePassword";
            case "deleteUser":
                // 用户管理：删除用户
                return "/deleteUser";
            case "getUser":
                // 用户管理：获取用户列表
                return "/getUserList";
            case "getRoleSelect":
                // 用户管理：获取角色下拉框
                return "/getRoleSelect";
            case "createRole":
                // 角色管理：创建角色
                return "/createRole";
            case "modifyRole":
                // 角色管理：修改角色
                return "/modifyRole";
            case "deleteRole":
                // 角色管理：删除角色
                return "/deleteRole";
            case "getRole":
                // 角色管理：获取角色列表
                return "/getRoleList";
            case "getParam":
                // 数据录入：获取分类和EC2
                return "/getTypeAndEC2";
            case "createData":
                // 数据录入：创建数据
                return "/manualInput";
            case "inputFile":
                // 数据录入：批量导入
                return "/excel_operate";
            case "getDataList":
                // 数据管理：获取数据列表
                return "/getData";
            case "modifyData":
                // 数据管理：修改数据
                return "/modifyInput";
            case "addExamine":
                // 数据管理：新增待审核数据
                return "/addExamine";
            case "deleteData":
                // 数据管理：删除数据
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
            // case "getTypeList":
            //     return "/getType";
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
            case "getSpiderRecord":
                // 系统管理：获取爬虫记录
                return "/getCrawlerNote";
            case "startSpiderTiming":
                // 系统管理：启动定时爬虫
                return "/startCrawler";
            case "closeSpiderTiming":
                // 系统管理：启动定时爬虫
                return "/stopCrawler";
            case "setSpiderTiming":
                // 系统管理：启动定时爬虫
                return "/setCrawlerTime";
            case "getExamineList":
                // 审核管理：获取审核数据列表
                return "/getExamineData";
            case "examinePass":
                // 审核管理：通过审核
                return "/marlboro";
            case "examineNoPass":
                // 审核管理：拒绝审核
                return "/refusal_examine";
            case "examinePassAll":
                // 审核管理：批量通过审核
                return "/marlboro_batch";
            case "examineNoPassAll":
                // 审核管理：批量拒绝审核
                return "/refusal_examine_batch";
        }
    }
};