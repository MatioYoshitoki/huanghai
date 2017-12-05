-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 2017-12-04 01:27:12
-- 服务器版本： 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hhyjs`
--

-- --------------------------------------------------------

--
-- 表的结构 `config`
--

CREATE TABLE `config` (
  `param` varchar(20) COLLATE utf8_bin NOT NULL,
  `value` varchar(4096) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `config`
--

INSERT INTO `config` (`param`, `value`) VALUES
('browseContent', 'The Summary page describing a given peptidase can be reached by use of an index under its Name, MEROPS Identifier or source Organism. The Summary describes the classification and nomenclature of the peptidase and offers links to supplementary pages showing sequence identifiers, the structure if known, literature references and more.<br/>The MEROPS database uses an hierarchical, structure-based classification of the peptidases. In this, each peptidase is assigned to a Family on the basis of statistically significant similarities in amino acid sequence, and families that are thought to be homologous are grouped together in a Clan. There is a Summary page for each family and clan, and these again have indexes. Each of the Summary pages offers links to supplementary pages.'),
('browseTitle', 'Marine Microorganism Enzyme(MME)'),
('indexBannerContent', '<strong>The marine microorganism active</br>product database</strong></br>Releas 1.0'),
('indexBannerTitle', 'MMAP'),
('indexContent', 'The Information database of MMAP is an information resource for marine microorganism active products. It gives us the basic experimental and theoretical data for the active products that originate from marine microorganism, including the sub database of marine microorganism, marine enzyme and the resources from deep sea.Please use the Menu in the bottom to the sub database.<br/>The MMAP team is the Marine product resources and Enzyme Engineering Laboratory at the Yellow Sea Fisheries Research Institute of Chinese Academy of Fishery Sciences, NO.106, Nanjing Road, Qingdao, Shandong, China(jixf@ysri.ac.cn)'),
('indexLinkText', 'Marine Microorganism Enzyme(MME)'),
('indexLinkUrl', 'browse.html'),
('indexTitle', 'MMAP(Information database of Marine Microorganism Active Product)'),
('isStart', '0'),
('isTiming', '0');

-- --------------------------------------------------------

--
-- 表的结构 `ec1`
--

CREATE TABLE `ec1` (
  `id` int(11) NOT NULL COMMENT '酶大类序号',
  `ec1` varchar(30) COLLATE utf8_bin NOT NULL COMMENT '酶大类'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `ec1`
--

INSERT INTO `ec1` (`id`, `ec1`) VALUES
(1, 'Hydrolases'),
(2, 'Isomerases'),
(3, 'Ligases'),
(4, 'Lyases'),
(5, 'oxidoreductases'),
(6, 'transferase');

-- --------------------------------------------------------

--
-- 表的结构 `ec2`
--

CREATE TABLE `ec2` (
  `id` int(11) NOT NULL COMMENT '酶种序号',
  `ec1Id` int(11) NOT NULL COMMENT '酶大类序号',
  `ec2` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '酶种'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

--
-- 转存表中的数据 `ec2`
--

INSERT INTO `ec2` (`id`, `ec1Id`, `ec2`) VALUES
(1, 1, 'acetylcholinesterase'),
(2, 1, 'acetylesterase'),
(7, 1, 'acetylgalactosaminidase'),
(8, 1, 'acetylglucosaminidase'),
(9, 1, 'acetylhydrolase'),
(10, 1, 'acetylmuramidase'),
(11, 1, 'acylamidase'),
(12, 1, 'acylase'),
(13, 1, 'acylhydrolase'),
(14, 1, 'acylphosphatase'),
(15, 1, 'adenosinetriphosphatase'),
(16, 1, 'adenosylhomocysteinase'),
(17, 1, 'adenylylsulfatase'),
(18, 1, 'agarase'),
(19, 1, 'agmatinase'),
(20, 1, 'alkylacetylglycerophosphatase'),
(21, 1, 'alkylamidase'),
(22, 1, 'alkylhalidase'),
(23, 1, 'alkylsulfatase'),
(24, 1, 'allantoicase'),
(25, 1, 'allantoinase'),
(26, 1, 'amidase'),
(27, 1, 'amidinoaspartase');

-- --------------------------------------------------------

--
-- 表的结构 `examine`
--

CREATE TABLE `examine` (
  `id` int(11) NOT NULL,
  `type` varchar(30) COLLATE utf8_bin NOT NULL,
  `ec1` varchar(20) COLLATE utf8_bin NOT NULL,
  `ec2` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(100) COLLATE utf8_bin NOT NULL,
  `locus` varchar(12) COLLATE utf8_bin NOT NULL,
  `pdbId` char(4) COLLATE utf8_bin NOT NULL,
  `dbsource` varchar(256) COLLATE utf8_bin DEFAULT '',
  `microbe` varchar(100) COLLATE utf8_bin DEFAULT '',
  `source` varchar(256) COLLATE utf8_bin DEFAULT '',
  `organism` varchar(256) COLLATE utf8_bin DEFAULT '',
  `date` char(11) COLLATE utf8_bin NOT NULL,
  `country` varchar(20) COLLATE utf8_bin DEFAULT '',
  `deepSea` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '是否深海',
  `temperature` varchar(10) COLLATE utf8_bin DEFAULT '' COMMENT '温度',
  `ph` varchar(5) COLLATE utf8_bin DEFAULT '' COMMENT '酸碱度',
  `zone` varchar(256) COLLATE utf8_bin DEFAULT '' COMMENT '区域',
  `cofactors` varchar(256) COLLATE utf8_bin DEFAULT '' COMMENT '辅酶因子',
  `inhibitors` varchar(256) COLLATE utf8_bin DEFAULT '',
  `origin` varchar(2048) COLLATE utf8_bin DEFAULT '',
  `author1` varchar(100) COLLATE utf8_bin DEFAULT '',
  `title1` varchar(100) COLLATE utf8_bin DEFAULT '',
  `journal1` varchar(100) COLLATE utf8_bin DEFAULT '',
  `pubmed1` varchar(20) COLLATE utf8_bin DEFAULT '',
  `abstract1` varchar(2048) COLLATE utf8_bin DEFAULT '',
  `author2` varchar(100) COLLATE utf8_bin DEFAULT '',
  `title2` varchar(100) COLLATE utf8_bin DEFAULT '',
  `journal2` varchar(100) COLLATE utf8_bin DEFAULT '',
  `pubmed2` varchar(20) COLLATE utf8_bin DEFAULT '',
  `abstract2` varchar(2048) COLLATE utf8_bin DEFAULT '',
  `author3` varchar(100) COLLATE utf8_bin DEFAULT '',
  `title3` varchar(100) COLLATE utf8_bin DEFAULT '',
  `journal3` varchar(100) COLLATE utf8_bin DEFAULT '',
  `pubmed3` varchar(20) COLLATE utf8_bin DEFAULT '',
  `abstract3` varchar(2048) COLLATE utf8_bin DEFAULT '',
  `author4` varchar(100) COLLATE utf8_bin DEFAULT '',
  `title4` varchar(100) COLLATE utf8_bin DEFAULT '',
  `journal4` varchar(100) COLLATE utf8_bin DEFAULT '',
  `pubmed4` varchar(20) COLLATE utf8_bin DEFAULT '',
  `abstract4` varchar(2048) COLLATE utf8_bin DEFAULT '',
  `modifyDate` char(19) COLLATE utf8_bin DEFAULT '' COMMENT '修改时间',
  `modifier` varchar(12) COLLATE utf8_bin DEFAULT '未修改' COMMENT '修改者',
  `note` varchar(4096) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT '备注'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- 表的结构 `mme`
--

CREATE TABLE `mme` (
  `id` int(11) NOT NULL,
  `type` varchar(30) COLLATE utf8_bin NOT NULL,
  `ec1` varchar(20) COLLATE utf8_bin NOT NULL,
  `ec2` varchar(30) COLLATE utf8_bin DEFAULT NULL,
  `title` varchar(100) COLLATE utf8_bin NOT NULL,
  `locus` varchar(12) COLLATE utf8_bin NOT NULL,
  `pdbId` char(4) COLLATE utf8_bin NOT NULL,
  `dbsource` varchar(256) COLLATE utf8_bin DEFAULT '',
  `microbe` varchar(100) COLLATE utf8_bin DEFAULT '',
  `source` varchar(256) COLLATE utf8_bin DEFAULT '',
  `organism` varchar(256) COLLATE utf8_bin DEFAULT '',
  `date` char(11) COLLATE utf8_bin NOT NULL,
  `country` varchar(20) COLLATE utf8_bin DEFAULT '',
  `deepSea` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '是否深海',
  `temperature` varchar(10) COLLATE utf8_bin DEFAULT '' COMMENT '温度',
  `ph` varchar(5) COLLATE utf8_bin DEFAULT '' COMMENT '酸碱度',
  `zone` varchar(256) COLLATE utf8_bin DEFAULT '' COMMENT '区域',
  `cofactors` varchar(256) COLLATE utf8_bin DEFAULT '' COMMENT '辅酶因子',
  `inhibitors` varchar(256) COLLATE utf8_bin DEFAULT '',
  `origin` varchar(2048) COLLATE utf8_bin DEFAULT '',
  `author1` varchar(100) COLLATE utf8_bin DEFAULT '',
  `title1` varchar(100) COLLATE utf8_bin DEFAULT '',
  `journal1` varchar(100) COLLATE utf8_bin DEFAULT '',
  `pubmed1` varchar(20) COLLATE utf8_bin DEFAULT '',
  `abstract1` varchar(2048) COLLATE utf8_bin DEFAULT '',
  `author2` varchar(100) COLLATE utf8_bin DEFAULT '',
  `title2` varchar(100) COLLATE utf8_bin DEFAULT '',
  `journal2` varchar(100) COLLATE utf8_bin DEFAULT '',
  `pubmed2` varchar(20) COLLATE utf8_bin DEFAULT '',
  `abstract2` varchar(2048) COLLATE utf8_bin DEFAULT '',
  `author3` varchar(100) COLLATE utf8_bin DEFAULT '',
  `title3` varchar(100) COLLATE utf8_bin DEFAULT '',
  `journal3` varchar(100) COLLATE utf8_bin DEFAULT '',
  `pubmed3` varchar(20) COLLATE utf8_bin DEFAULT '',
  `abstract3` varchar(2048) COLLATE utf8_bin DEFAULT '',
  `author4` varchar(100) COLLATE utf8_bin DEFAULT '',
  `title4` varchar(100) COLLATE utf8_bin DEFAULT '',
  `journal4` varchar(100) COLLATE utf8_bin DEFAULT '',
  `pubmed4` varchar(20) COLLATE utf8_bin DEFAULT '',
  `abstract4` varchar(2048) COLLATE utf8_bin DEFAULT '',
  `operator` varchar(10) COLLATE utf8_bin DEFAULT '爬虫' COMMENT '录入者',
  `operateDate` char(19) COLLATE utf8_bin NOT NULL COMMENT '录入时间',
  `modifyDate` char(19) COLLATE utf8_bin DEFAULT '' COMMENT '修改时间',
  `modifier` varchar(10) COLLATE utf8_bin DEFAULT '未修改' COMMENT '修改者',
  `isModified` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '是否在审核'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `mme`
--

INSERT INTO `mme` (`id`, `type`, `ec1`, `ec2`, `title`, `locus`, `pdbId`, `dbsource`, `microbe`, `source`, `organism`, `date`, `country`, `deepSea`, `temperature`, `ph`, `zone`, `cofactors`, `inhibitors`, `origin`, `author1`, `title1`, `journal1`, `pubmed1`, `abstract1`, `author2`, `title2`, `journal2`, `pubmed2`, `abstract2`, `author3`, `title3`, `journal3`, `pubmed3`, `abstract3`, `author4`, `title4`, `journal4`, `pubmed4`, `abstract4`, `operator`, `operateDate`, `modifyDate`, `modifier`, `isModified`) VALUES
(1, 'marine', 'Hydrolases', 'acetylmuramidase', 'Chain A, Structure Analysis Of A New Psychrophilic Marine Protease', '3U1R_A', '3U1R', ' pdb: molecule 3U1R, chain 65, release Oct 31, 2012;\n deposition: Sep 30, 2011;\n class: Hydrolase;\n source: Mmdb_id: 104457, Pdb_id 1: 3U1R;\n            Exp. method: X-Ray Diffraction.', NULL, 'Flavobacterium sp. YS-80-122', 'Flavobacterium sp. YS-80-122\nBacteria; Bacteroidetes; Flavobacteriia; Flavobacteriales;\nFlavobacteriaceae; Flavobacterium.', '02-NOV-2012', 'china', '0', '', '', '', '', '', '1 msklkekaal svnptfaang tssaftqvdn fshfydrgnh lvngkpsftv dqaadqltrs 61 gaswydlngd gvinlsytfl tspppgyasr glgtfssfsg lqkeqaklsl eswadvakvt 121 ftegpaargd', 'Zhang,S.C., Sun,M., Li, Zhou,M', 'Structure analysis of a new ', ' PLoS ONE 6 (11), E26939 (2011)', '22132082', '', '', 'Direct Submission', 'Submitted (30-SEP-2011)', '', '', '', '', '', '', '', '', '', '', '', '', '陈煦', '2017-11-21 18:40:33', '2017-12-03 10:53:28', '超级用户', '0'),
(7, 'marine', '', 'acetylcholinesterase', 'asdasd', 'sadasd', 'sada', '', NULL, '', '', 'sdfsdf', '', '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '超级用户', '2017-11-26 15:07:38', '2017-11-26 15:07:38', '未修改', '0'),
(8, 'marine', 'Hydrolases', 'acetylmuramidase', 'TEST1', '8S6D_T', '8S6D', '新增DBSOURCE', NULL, '', '', 'SDASDA', '', '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '超级用户', '2017-11-28 09:41:25', '2017-12-03 10:53:47', '超级用户', '0'),
(10, 'north pole', 'Hydrolases', 'acetylcholinesterase', 'sadasda', '3D5W_2', '3D5W', '', NULL, '', '', 'sdasd', '', '0', '76', '7.1', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '超级用户', '2017-11-28 11:01:03', '2017-12-02 15:32:39', '超级用户', '0'),
(11, 'marine', 'ocean', 'acylase', 'penicillin acylase family protein [marine gamma proteobacterium HTCC2143]', 'WP_007224474', 'WP_0', '', NULL, 'marine gamma proteobacterium HTCC2143', 'marine gamma proteobacterium HTCC2143\nBacteria; Proteobacteria; Gammaproteobacteria; Cellvibrionales;\nSpongiibacteraceae; BD1-7 clade.', '13-MAY-2017', 'china', '0', '', '', '', '', '', '1 mnikitaaeq pfdicwdang lvhvfaenqa dafrgmgyaa gyerlwqihl stlfatgtaa\n61 svmgpryiaq dlmhkafnvp aydmpdspgd wvvdaylegl nsyvrglqei paefiragtv\n121 preftrhdva srhrftgwfq hktfmekiym gklmarhgts wfkhhlhnfs aadeqsiael\n181 rdallsmdag ignllfpnqr ivsgsnnwav rghlsasgkp mlatdphqph sipntffyvh\n241 lstpdwdtfg atfpgipyfm mgfnrnvswg lttgfidtfd vfvereeapr skqfiidiag\n301 qspasfdidi sdrgpilesl tdelglstpt trqyttslnw vmkdiptsag tlalmpmsen\n361 seefgnslfe ndvcplvnni icvdqddnlr rfiaatiqkr pgykrpnqds hgvtgsvpla\n421 awrheynfdi skadeltvey npecgyslta nddtmedrgs fpihnyptfn arakriqall\n481 dekisknshp mftssdfesm qqdildlraq etvpgiiqcl tdpsasvqra rellenwnfr\n541 agldsqaaci yyplmdkqws mqfmhkvlgd dlilslptva valnkfsvdd ffqenspwlv\n601 hrktldkiic devkklidnl dnemgdqwaw gelhqinfkh slskfnewgh mnagpdpiag\n661 sgttlrmalh ipakddtekv rvyhgpafrw vvdladplhf rfviaggnsg kpesshfadq\n721 ydywlnsqyy dvslvrdeld ieevihan', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '超级用户', '2017-11-28 14:24:09', '2017-11-28 14:24:09', '未修改', '0'),
(12, 'marine', 'Hydrolases', 'acetylesterase', 'test2', 'sadasdsdad', 'sdas', '', NULL, '', '', 'asdasdsad', '', '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '超级用户', '2017-11-28 14:41:45', '2017-11-28 15:53:50', '超级用户', '0'),
(13, 'marine', 'Hydrolases', 'acetylcholinesterase', 'sadasdas', 'sadasdsd', 'sdsa', '', NULL, '', '', 'sdasddsa', '', '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '超级用户', '2017-11-28 15:44:31', '2017-11-28 16:20:27', '超级用户', '0'),
(14, 'marine', 'Hydrolases', 'acetylglucosaminidase', 'asdasdsadd', 'sdasdadwd', 'sdas', '', NULL, '', '', 'adasdasda', '', '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '超级用户', '2017-11-28 15:46:18', '2017-11-28 15:46:18', '未修改', '0'),
(15, 'marine', 'Hydrolases', 'acetylcholinesterase', 'test', 'test', 'test', '', NULL, '', '', '02-DEC-2017', '', '0', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '', '超级用户', '2017-12-02 15:01:18', '2017-12-02 15:01:18', '未修改', '0'),
(16, 'marine', 'Hyloses', 'acylase', 'TestExcel', '3U1E_5', '3U1E', '', '', '', '', 'NOV-27-2017', '', '', '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', NULL, '', '', '', '', NULL, '', '', '', '', NULL, '', '陈煦', '2017-12-03 11:13:22', '2017-12-03 11:13:22', '未修改', '0'),
(17, 'marine', 'Hyloses', 'acylase', 'TestExcel', '3U1F_6', '3U1F', '', '', '', '', 'NOV-27-2017', '', '', '', '', '', '', '', '', '', '', '', NULL, '', '', '', '', NULL, '', '', '', '', NULL, '', '', '', '', NULL, '', '陈煦', '2017-12-03 11:25:18', '2017-12-03 11:25:18', '未修改', '0');

-- --------------------------------------------------------

--
-- 表的结构 `record`
--

CREATE TABLE `record` (
  `RID` int(11) NOT NULL COMMENT '记录号',
  `userAccount` varchar(12) COLLATE utf8_bin NOT NULL COMMENT '接收记录人账号',
  `userName` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '操作人姓名',
  `operation` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '操作内容',
  `date` char(19) COLLATE utf8_bin NOT NULL COMMENT '操作日期'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- 表的结构 `role`
--

CREATE TABLE `role` (
  `roleId` int(11) NOT NULL COMMENT '角色号',
  `roleName` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '角色名称',
  `roleAuth` varchar(20) COLLATE utf8_bin NOT NULL COMMENT '拥有权限',
  `roleCreate` char(19) COLLATE utf8_bin NOT NULL COMMENT '创建日期',
  `roleModify` char(19) COLLATE utf8_bin DEFAULT '' COMMENT '修改日期',
  `operator` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '操作者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `role`
--

INSERT INTO `role` (`roleId`, `roleName`, `roleAuth`, `roleCreate`, `roleModify`, `operator`) VALUES
(1, '系统管理员', '1|2|3|4|5|6', '2017-11-21 11:31:04', '2017-11-21 12:09:30', '陈煦'),
(2, '系统使用人员', '3|4', '2017-11-26 18:28:03', '2017-11-26 18:28:03', '超级用户'),
(3, '测试权限角色管理', '2|5', '2017-12-02 14:29:44', '2017-12-02 14:36:26', '超级用户'),
(4, '测试权限之数据录入', '3', '2017-12-02 14:30:48', '2017-12-02 14:30:48', '超级用户'),
(5, '测试权限之数据管理', '4', '2017-12-02 14:31:06', '2017-12-02 14:31:06', '超级用户'),
(7, '测试权限之审核管理', '6', '2017-12-02 14:33:24', '2017-12-02 14:33:24', '超级用户');

-- --------------------------------------------------------

--
-- 表的结构 `type`
--

CREATE TABLE `type` (
  `tid` int(11) NOT NULL,
  `type` varchar(20) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `type`
--

INSERT INTO `type` (`tid`, `type`) VALUES
(1, 'marine'),
(2, 'sea'),
(3, 'ocean'),
(4, 'south pole'),
(5, 'north pole'),
(6, 'antarctinc'),
(7, 'arctic');

-- --------------------------------------------------------

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `userName` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '用户姓名',
  `userAccount` varchar(12) COLLATE utf8_bin NOT NULL COMMENT '用户账号',
  `userRole` int(11) NOT NULL COMMENT '分配角色',
  `password` varchar(40) COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  `userGender` char(1) COLLATE utf8_bin NOT NULL COMMENT '用户性别',
  `userCreate` char(19) COLLATE utf8_bin NOT NULL COMMENT '创建日期',
  `userModify` char(19) COLLATE utf8_bin DEFAULT '' COMMENT '修改日期',
  `operator` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '操作者'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`userName`, `userAccount`, `userRole`, `password`, `userGender`, `userCreate`, `userModify`, `operator`) VALUES
('陈煦', 'clvsit', 2, 'e10adc3949ba59abbe56e057f20f883e', '0', '2017-11-26 18:28:16', '2017-11-26 18:28:16', '超级用户'),
('超级用户', 'manager', 1, 'e10adc3949ba59abbe56e057f20f883e', '0', '2017-11-22 14:32:42', '', ''),
('测是', 'tester1', 1, 'e10adc3949ba59abbe56e057f20f883e', '0', '2017-12-02 13:47:43', '2017-12-02 14:16:17', '超级用户'),
('测试二号', 'tester2', 2, 'e10adc3949ba59abbe56e057f20f883e', '0', '2017-12-02 13:50:40', '2017-12-02 13:50:40', '超级用户'),
('测试三号', 'tester3', 2, 'e10adc3949ba59abbe56e057f20f883e', '0', '2017-12-02 13:52:41', '2017-12-02 13:52:41', '超级用户'),
('测试四号', 'tester4', 2, 'e10adc3949ba59abbe56e057f20f883e', '1', '2017-12-02 13:52:52', '2017-12-02 13:52:52', '超级用户'),
('测试五号', 'tester5', 2, 'e10adc3949ba59abbe56e057f20f883e', '0', '2017-12-02 13:53:15', '2017-12-02 13:53:15', '超级用户');

-- --------------------------------------------------------

--
-- 替换视图以便查看 `view_ec`
-- (See below for the actual view)
--
CREATE TABLE `view_ec` (
`id` int(11)
,`ec1` varchar(30)
,`ec2` varchar(40)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `view_front`
-- (See below for the actual view)
--
CREATE TABLE `view_front` (
`locus` varchar(12)
,`ec1` varchar(20)
,`ec2` varchar(30)
,`title` varchar(100)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `view_manager`
-- (See below for the actual view)
--
CREATE TABLE `view_manager` (
`account` varchar(12)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `view_me`
-- (See below for the actual view)
--
CREATE TABLE `view_me` (
`userName` varchar(10)
,`userAccount` varchar(12)
,`userGender` char(1)
,`roleName` varchar(20)
,`roleAuth` varchar(20)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `view_param`
-- (See below for the actual view)
--
CREATE TABLE `view_param` (
`type` varchar(20)
,`ec2` varchar(40)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `view_spider_record`
-- (See below for the actual view)
--
CREATE TABLE `view_spider_record` (
`number` bigint(21)
,`operateDate` date
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `view_type`
-- (See below for the actual view)
--
CREATE TABLE `view_type` (
`type` varchar(30)
,`count` bigint(21)
);

-- --------------------------------------------------------

--
-- 替换视图以便查看 `view_user`
-- (See below for the actual view)
--
CREATE TABLE `view_user` (
);

-- --------------------------------------------------------

--
-- 视图结构 `view_ec`
--
DROP TABLE IF EXISTS `view_ec`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_ec`  AS  select `ec2`.`id` AS `id`,`ec1`.`ec1` AS `ec1`,`ec2`.`ec2` AS `ec2` from (`ec1` join `ec2` on((`ec1`.`id` = `ec2`.`ec1Id`))) ;

-- --------------------------------------------------------

--
-- 视图结构 `view_front`
--
DROP TABLE IF EXISTS `view_front`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_front`  AS  select `mme`.`locus` AS `locus`,`mme`.`ec1` AS `ec1`,`mme`.`ec2` AS `ec2`,`mme`.`title` AS `title` from `mme` ;

-- --------------------------------------------------------

--
-- 视图结构 `view_manager`
--
DROP TABLE IF EXISTS `view_manager`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_manager`  AS  select `user`.`userAccount` AS `account` from `user` where (`user`.`userRole` = '1') ;

-- --------------------------------------------------------

--
-- 视图结构 `view_me`
--
DROP TABLE IF EXISTS `view_me`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_me`  AS  select `u`.`userName` AS `userName`,`u`.`userAccount` AS `userAccount`,`u`.`userGender` AS `userGender`,`r`.`roleName` AS `roleName`,`r`.`roleAuth` AS `roleAuth` from (`user` `u` join `role` `r` on((`u`.`userRole` = `r`.`roleId`))) ;

-- --------------------------------------------------------

--
-- 视图结构 `view_param`
--
DROP TABLE IF EXISTS `view_param`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_param`  AS  select `type`.`type` AS `type`,`ec2`.`ec2` AS `ec2` from (`type` join `ec2`) ;

-- --------------------------------------------------------

--
-- 视图结构 `view_spider_record`
--
DROP TABLE IF EXISTS `view_spider_record`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_spider_record`  AS  select count(0) AS `number`,cast(`mme`.`operateDate` as date) AS `operateDate` from `mme` group by cast(`mme`.`operateDate` as date) order by cast(`mme`.`operateDate` as date) desc ;

-- --------------------------------------------------------

--
-- 视图结构 `view_type`
--
DROP TABLE IF EXISTS `view_type`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_type`  AS  select `mme`.`type` AS `type`,count(0) AS `count` from `mme` group by `mme`.`type` ;

-- --------------------------------------------------------

--
-- 视图结构 `view_user`
--
DROP TABLE IF EXISTS `view_user`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `view_user`  AS  select `u`.`userId` AS `userId`,`u`.`userName` AS `userName`,`u`.`userAccount` AS `userAccount`,`u`.`userRole` AS `userRole`,`u`.`userGender` AS `userGender`,`u`.`userCreate` AS `userCreate`,`u`.`userModify` AS `userModify`,`u`.`operator` AS `operator`,`r`.`roleName` AS `roleName` from (`user` `u` join `role` `r` on((`u`.`userRole` = `r`.`roleId`))) ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `config`
--
ALTER TABLE `config`
  ADD PRIMARY KEY (`param`);

--
-- Indexes for table `ec1`
--
ALTER TABLE `ec1`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `ec2`
--
ALTER TABLE `ec2`
  ADD PRIMARY KEY (`id`),
  ADD KEY `ec1_constraints` (`ec1Id`);

--
-- Indexes for table `examine`
--
ALTER TABLE `examine`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `locus` (`locus`);

--
-- Indexes for table `mme`
--
ALTER TABLE `mme`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `locus` (`locus`);

--
-- Indexes for table `record`
--
ALTER TABLE `record`
  ADD PRIMARY KEY (`RID`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`roleId`);

--
-- Indexes for table `type`
--
ALTER TABLE `type`
  ADD PRIMARY KEY (`tid`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userAccount`),
  ADD UNIQUE KEY `userAccount` (`userAccount`),
  ADD KEY `roleConstraint` (`userRole`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `ec1`
--
ALTER TABLE `ec1`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '酶大类序号', AUTO_INCREMENT=7;
--
-- 使用表AUTO_INCREMENT `ec2`
--
ALTER TABLE `ec2`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '酶种序号', AUTO_INCREMENT=28;
--
-- 使用表AUTO_INCREMENT `examine`
--
ALTER TABLE `examine`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- 使用表AUTO_INCREMENT `mme`
--
ALTER TABLE `mme`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- 使用表AUTO_INCREMENT `record`
--
ALTER TABLE `record`
  MODIFY `RID` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录号';
--
-- 使用表AUTO_INCREMENT `role`
--
ALTER TABLE `role`
  MODIFY `roleId` int(11) NOT NULL AUTO_INCREMENT COMMENT '角色号', AUTO_INCREMENT=8;
--
-- 使用表AUTO_INCREMENT `type`
--
ALTER TABLE `type`
  MODIFY `tid` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- 限制导出的表
--

--
-- 限制表 `ec2`
--
ALTER TABLE `ec2`
  ADD CONSTRAINT `ec1_constraints` FOREIGN KEY (`ec1Id`) REFERENCES `ec1` (`id`);

--
-- 限制表 `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `roleConstraint` FOREIGN KEY (`userRole`) REFERENCES `role` (`roleId`) ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
