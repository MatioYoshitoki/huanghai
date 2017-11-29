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

import java.sql.SQLException;
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


    @RequestMapping(value = "/getExamineData", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getExamineData(
            @RequestParam(Keys.TYPE) String type,
            @RequestParam(Keys.STARTDATE) String startDate,
            @RequestParam(Keys.ENDDATE) String endDate,
            @RequestParam(Keys.NUMBERPERPAGE) String numberPerPage,
            @RequestParam(Keys.STARTPOS) String startPos,
            @RequestParam(Keys.PARAM) String param
    ){
        MmeCondition condition = new MmeCondition();
        int startPos_int = Integer.valueOf(startPos);
        int numberPerPage_int = Integer.valueOf(numberPerPage);
        int startSize = (startPos_int - 1) * numberPerPage_int;
        int endSize = (startPos_int) * numberPerPage_int ;
        condition.setStartSize(startSize);;
        condition.setEndSize(endSize);
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
                condition.setEc2(param);
                break;
            case "4":
                condition.setOperator(param);
                break;
            case "5":
                condition.setModifier(param);
                break;
            case "6":
                startDate += "00:00:00";
                endDate += "23:59:59";
                condition.setEndDate_op(startDate);
                condition.setEndDate_op(endDate);
                break;
            case "7":
                startDate += "00:00:00";
                endDate += "23:59:59";
                condition.setStartDate_md(startDate);
                condition.setEndDate_md(endDate);
                break;
            default:
                return "";
        }
        List<Examine> examines = examineMapper.selectByCondition(condition);
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        JSONArray data = new JSONArray();
        if (examines == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GET_EXAMINE_DATA_FAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        for (Examine examine :examines){
            JSONObject buff = new JSONObject();
            buff.put(Keys.MMEID, examine.getId());
            buff.put(Keys.TITLE, examine.getTitle());
            buff.put(Keys.TITLE1, examine.getTitle1());
            buff.put(Keys.TITLE2, examine.getTitle2());
            buff.put(Keys.TITLE3, examine.getTitle3());
            buff.put(Keys.TITLE4, examine.getTitle4());
            buff.put(Keys.ABSTRACT1, examine.getAbstract1());
            buff.put(Keys.ABSTRACT2, examine.getAbstract2());
            buff.put(Keys.ABSTRACT3, examine.getAbstract3());
            buff.put(Keys.ABSTRACT4, examine.getAbstract4());
            buff.put(Keys.AUTHOR1, examine.getAuthor1());
            buff.put(Keys.AUTHOR2, examine.getAuthor2());
            buff.put(Keys.AUTHOR3, examine.getAuthor3());
            buff.put(Keys.AUTHOR4, examine.getAuthor4());
            buff.put(Keys.JOURNAL1, examine.getJournal1());
            buff.put(Keys.JOURNAL2, examine.getJournal2());
            buff.put(Keys.JOURNAL3, examine.getJournal3());
            buff.put(Keys.JOURNAL4, examine.getJournal4());
            buff.put(Keys.PUBMED1, examine.getPubmed1());
            buff.put(Keys.PUBMED2, examine.getPubmed2());
            buff.put(Keys.PUBMED3, examine.getPubmed3());
            buff.put(Keys.PUBMED4, examine.getPubmed4());

            buff.put(Keys.COUNTRY, examine.getCountry());
            buff.put(Keys.LOCUS, examine.getLocus());
            buff.put(Keys.MICROBE, examine.getMicrobe());
            buff.put(Keys.EC2, examine.getEc2());
            buff.put(Keys.SOURCE, examine.getSource());
            buff.put(Keys.DBSOURCE, examine.getDbsource());
            buff.put(Keys.DATE, examine.getDate());
            buff.put(Keys.ORGANISM, examine.getOrganism());
            buff.put(Keys.ORIGIN, examine.getOrigin());
            buff.put(Keys.MODIFIER, examine.getModifier());
            buff.put(Keys.MODIFYDATE, examine.getModifydate());
            buff.put(Keys.PDBID, examine.getPdbid());
            buff.put(Keys.TYPE, examine.getType());
            data.add(buff);

        }
        result.put(Keys.DATA,data);
        result.put(Keys.MSG,"");
        result.put(Keys.COUNT,examines.size());
        return result.toJSONString();
    }

    @RequestMapping(value = "/marlboro", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String marlboro(
            @RequestParam(Keys.ID) String id
    ){


        Examine examine = examineMapper.selectByPrimaryKey(Integer.valueOf(id));

        JSONObject result ;


        if (examine==null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.MARLBORO_FAILD);
            return result.toJSONString();
        }

        return mmeService.marlboro(examine);
    }
    @RequestMapping(value = "/marlboro_batch", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String marlboro(){

        List<Examine> examines = examineMapper.selectAll();
        JSONObject result ;
        if (examines == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.MARLBORO_BATCH_FAILD);
            return result.toJSONString();
        }
        return mmeService.marlboro_batch(examines);
    }
    @RequestMapping(value = "/refusal_examine", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String refusalExamine(
            @RequestParam(Keys.ID) String id
    ){
        Examine examine = examineMapper.selectByPrimaryKey(Integer.valueOf(id));
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        if (examine==null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.REFUSAL_EXAMINE_FAILD);
            return result.toJSONString();
        }
        examineMapper.deleteByPrimaryKey(Integer.valueOf(id));
        result.put(Keys.DATA,new JSONObject());
        result.put(Keys.MSG,Errors.REFUSAL_EXAMINE_SUCCESS);
        return result.toJSONString();
    }
    @RequestMapping(value = "/refusal_examine_batch", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String refusalExamineBatch(){

        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        if (examineMapper.selectAll()==null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.REFUSAL_EXAMINE_BATCH_FAILD);
            return result.toJSONString();
        }
        examineMapper.deleteAll();
        result.put(Keys.DATA,new JSONObject());
        result.put(Keys.MSG,Errors.REFUSAL_EXAMINE_BATCH_SUCCESS);
        return result.toJSONString();
    }

}
