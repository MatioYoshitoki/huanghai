package com.matio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.ExamineMapper;
import com.matio.mapping.MmeMapper;
import com.matio.pojo.Examine;
import com.matio.pojo.Mme;
import com.matio.pojo.MmeCondition;
import com.matio.services.intf.IMmeService;
import com.matio.unit.JsonUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by matioyoshitoki on 2017/11/26.
 */
@RestController
public class ExamineController {
    @Autowired
    ExamineMapper examineMapper;
    @Autowired
    MmeMapper mmeMapper;
    @Autowired
    IMmeService mmeService;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(value = "/getExamineData", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getExamineData(
            @RequestParam(Keys.TYPE) String type,
            @RequestParam(Keys.STARTDATE) String startDate,
            @RequestParam(Keys.ENDDATE) String endDate,
            @RequestParam(Keys.NUMBERPERPAGE) String numberPerPage,
            @RequestParam(Keys.STARTPOS) String startPos,
            @RequestParam(Keys.PARAM) String param
    ){
        JSONObject result;
        try {
            MmeCondition condition = new MmeCondition();
            int startPos_int = Integer.valueOf(startPos);
            int numberPerPage_int = Integer.valueOf(numberPerPage);
            int startSize = (startPos_int - 1) * numberPerPage_int;
            condition.setStartSize(startSize);;
            condition.setEndSize(numberPerPage_int);
            switch (type){
                case "0":
                    break;
                case "1":
                    condition.setPdbId(param);
                    break;
                case "2":
                    condition.setType(param);
                    break;
                case "3":
                    condition.setEc1(param);
                    break;
                case "4":
                    condition.setEc2(param);
                    break;
                case "5":
                    try {
                        condition.setModifier(param.getBytes("utf8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    break;
                case "6":
                    startDate += " 00:00:00";
                    endDate += " 23:59:59";
                    condition.setStartDate_md(startDate);
                    condition.setEndDate_md(endDate);
                    break;
                default:
                    return "";
            }
            List<Examine> examines = examineMapper.selectByCondition(condition);
            int count = examineMapper.selectCountByCondition(condition);
            JSONArray data = new JSONArray();
            for (Examine examine :examines){
                JSONObject buff = new JSONObject();
                buff.put(Keys.MMEID, examine.getId());
                buff.put(Keys.TITLE,examine.getTitle());
                buff.put(Keys.TITLE1,examine.getTitle1());
                buff.put(Keys.TITLE2,examine.getTitle2());
                buff.put(Keys.TITLE3,examine.getTitle3());
                buff.put(Keys.TITLE4,examine.getTitle4());
                buff.put(Keys.ABSTRACT1,examine.getAbstract1());
                buff.put(Keys.ABSTRACT2,examine.getAbstract2());
                buff.put(Keys.ABSTRACT3,examine.getAbstract3());
                buff.put(Keys.ABSTRACT4,examine.getAbstract4());
                buff.put(Keys.AUTHOR1,examine.getAuthor1());
                buff.put(Keys.AUTHOR2,examine.getAuthor2());
                buff.put(Keys.AUTHOR3,examine.getAuthor3());
                buff.put(Keys.AUTHOR4,examine.getAuthor4());
                buff.put(Keys.JOURNAL1,examine.getJournal1());
                buff.put(Keys.JOURNAL2,examine.getJournal2());
                buff.put(Keys.JOURNAL3,examine.getJournal3());
                buff.put(Keys.JOURNAL4,examine.getJournal4());
                buff.put(Keys.PUBMED1,examine.getPubmed1());
                buff.put(Keys.PUBMED2,examine.getPubmed2());
                buff.put(Keys.PUBMED3,examine.getPubmed3());
                buff.put(Keys.PUBMED4,examine.getPubmed4());

                buff.put(Keys.COUNTRY,examine.getCountry());
                buff.put(Keys.LOCUS,examine.getLocus());
                buff.put(Keys.MICROBE,examine.getMicrobe());
                buff.put(Keys.EC1,examine.getEc1());
                buff.put(Keys.EC2,examine.getEc2());
                buff.put(Keys.SOURCE,examine.getSource());
                buff.put(Keys.DBSOURCE,examine.getDbsource());
                buff.put(Keys.DATE,examine.getDate());
                buff.put(Keys.ORGANISM,examine.getOrganism());
                buff.put(Keys.DEEPSEA,examine.getDeepsea());
                buff.put(Keys.TEMPERATURE,examine.getTemperature());
                buff.put(Keys.PH,examine.getPh());
                buff.put(Keys.ZONE,examine.getZone());
                buff.put(Keys.COFACTORS,examine.getCofactors());
                buff.put(Keys.INHIBITORS,examine.getInhibitors());
                buff.put(Keys.ORIGIN,examine.getOrigin());
                buff.put(Keys.MODIFYDATE,examine.getModifydate());
                buff.put(Keys.PDBID,examine.getPdbid());
                buff.put(Keys.TYPE,examine.getType());
                try {
                    buff.put(Keys.NOTE,new String(examine.getNote(), "utf8"));
                    buff.put(Keys.MODIFIER,new String(examine.getModifier(),"utf8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                data.add(buff);
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.DATA,data);
            result.put(Keys.MSG,"");
            result.put(Keys.COUNT,count);
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GET_EXAMINE_DATA_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/addExamine", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String addExamine(
            @RequestParam(Keys.TITLE) String title,
            @RequestParam(Keys.TITLE1) String title1,
            @RequestParam(Keys.TITLE2) String title2,
            @RequestParam(Keys.TITLE3) String title3,
            @RequestParam(Keys.TITLE4) String title4,
            @RequestParam(Keys.AUTHOR1) String author1,
            @RequestParam(Keys.AUTHOR2) String author2,
            @RequestParam(Keys.AUTHOR3) String author3,
            @RequestParam(Keys.AUTHOR4) String author4,
            @RequestParam(Keys.ABSTRACT1) String abstract1,
            @RequestParam(Keys.ABSTRACT2) String abstract2,
            @RequestParam(Keys.ABSTRACT3) String abstract3,
            @RequestParam(Keys.ABSTRACT4) String abstract4,
            @RequestParam(Keys.JOURNAL1) String journal1,
            @RequestParam(Keys.JOURNAL2) String journal2,
            @RequestParam(Keys.JOURNAL3) String journal3,
            @RequestParam(Keys.JOURNAL4) String journal4,
            @RequestParam(Keys.PUBMED1) String pubmed1,
            @RequestParam(Keys.PUBMED2) String pubmed2,
            @RequestParam(Keys.PUBMED3) String pubmed3,
            @RequestParam(Keys.PUBMED4) String pubmed4,
            @RequestParam(Keys.LOCUS) String locus,
            @RequestParam(Keys.PDBID) String pdbid,
            @RequestParam(Keys.DBSOURCE) String dbsource,
            @RequestParam(Keys.SOURCE) String source,
            @RequestParam(Keys.ORGANISM) String organsim,
            @RequestParam(Keys.DATE) String date,
            @RequestParam(Keys.COUNTRY) String country,
            @RequestParam(Keys.ORIGIN) String origin,
            @RequestParam(Keys.MODIFIER) String modifier,
            @RequestParam(Keys.TYPE) String type,
            @RequestParam(Keys.EC2) String ec2,
            @RequestParam(Keys.DEEPSEA) String deepSea,
            @RequestParam(Keys.TEMPERATURE) String temperature,
            @RequestParam(Keys.PH) String ph,
            @RequestParam(Keys.ZONE) String zone,
            @RequestParam(Keys.COFACTORS) String cofactors,
            @RequestParam(Keys.INHIBITORS) String inhibitors,
            @RequestParam(Keys.NOTE) String note,
            @RequestParam(Keys.MMEID) String id
    ){
        JSONObject result;
        try {
            Examine examine = examineMapper.selectByPrimaryKey(Integer.valueOf(id));
            examine = new Examine();
            String ec1 = mmeMapper.selectEC1ByEC2(ec2);
            Date now = new Date();
            examine.setAbstract1(abstract1);
            examine.setAbstract2(abstract2);
            examine.setAbstract3(abstract3);
            examine.setAbstract4(abstract4);
            examine.setTitle(title);
            examine.setTitle1(title1);
            examine.setTitle2(title2);
            examine.setTitle3(title3);
            examine.setTitle4(title4);
            examine.setJournal1(journal1);
            examine.setJournal2(journal2);
            examine.setJournal3(journal3);
            examine.setJournal4(journal4);
            examine.setPubmed1(pubmed1);
            examine.setPubmed2(pubmed2);
            examine.setPubmed3(pubmed3);
            examine.setPubmed4(pubmed4);
            examine.setAuthor1(author1);
            examine.setAuthor2(author2);
            examine.setAuthor3(author3);
            examine.setAuthor4(author4);
            examine.setLocus(locus);
            examine.setOrganism(organsim);
            examine.setOrigin(origin);
            examine.setPdbid(pdbid);
            examine.setDate(date);
            examine.setDbsource(dbsource);
            examine.setSource(source);
            examine.setCountry(country);
            examine.setType(type);
            examine.setEc1(ec1);
            examine.setEc2(ec2);
            examine.setId(Integer.valueOf(id));
            examine.setDeepsea(deepSea);
            examine.setTemperature(temperature);
            examine.setPh(ph);
            examine.setZone(zone);
            examine.setCofactors(cofactors);
            examine.setInhibitors(inhibitors);
            examine.setModifydate(simpleDateFormat.format(now));
            try {
                examine.setNote(note.getBytes("utf8"));
                examine.setModifier(modifier.getBytes("utf8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return mmeService.addExamine(examine);
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.ADDEXAMINE_FAILD);
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/marlboro", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String marlboro(
            @RequestParam(Keys.ID) String id
    ){
        JSONObject result;
        try {
            Examine examine = examineMapper.selectByPrimaryKey(Integer.valueOf(id));
            return mmeService.marlboro(examine);
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.MARLBORO_FAILD);
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/marlboro_batch", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String marlboro(){
        JSONObject result;
        try {
            List<Examine> examines = examineMapper.selectAll();
            return mmeService.marlboro_batch(examines);
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.MARLBORO_BATCH_FAILD);
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/refusal_examine", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String refusalExamine(
            @RequestParam(Keys.ID) String id
    ){
        JSONObject result;
        try {
            Examine examine = examineMapper.selectByPrimaryKey(Integer.valueOf(id));
            return mmeService.refuse(Integer.valueOf(id));
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.REFUSAL_EXAMINE_FAILD);
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/refusal_examine_batch", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String refusalExamineBatch(){
        JSONObject result;
        try {
            return mmeService.refuse_batch();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.REFUSAL_EXAMINE_BATCH_FAILD);
            return result.toJSONString();
        }
    }

}
