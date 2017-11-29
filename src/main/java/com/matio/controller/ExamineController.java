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
import com.matio.unit.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
            buff.put(Keys.OPERATOR, examine.getOperator());
            buff.put(Keys.MODIFIER, examine.getModifier());
            buff.put(Keys.OPERATEDATE, examine.getOperatedate());
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
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        if (examine==null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.MARLBORO_FAILD);
            return result.toJSONString();
        }
        Mme mme = new Mme();
        mme.setId(examine.getId());
        mme.setTitle(examine.getTitle());
        mme.setTitle1(examine.getTitle1());
        mme.setTitle2(examine.getTitle2());
        mme.setTitle3(examine.getTitle3());
        mme.setTitle4(examine.getTitle4());
        mme.setAbstract1(examine.getAbstract1());
        mme.setAbstract2(examine.getAbstract2());
        mme.setAbstract3(examine.getAbstract3());
        mme.setAbstract4(examine.getAbstract4());
        mme.setAuthor1(examine.getAuthor1());
        mme.setAuthor2(examine.getAuthor2());
        mme.setAuthor3(examine.getAuthor3());
        mme.setAuthor4(examine.getAuthor4());
        mme.setJournal1(examine.getJournal1());
        mme.setJournal2(examine.getJournal2());
        mme.setJournal3(examine.getJournal3());
        mme.setJournal4(examine.getJournal4());
        mme.setPubmed1(examine.getPubmed1());
        mme.setPubmed2(examine.getPubmed2());
        mme.setPubmed3(examine.getPubmed3());
        mme.setPubmed4(examine.getPubmed4());

        mme.setCountry(examine.getCountry());
        mme.setLocus(examine.getLocus());
        mme.setMicrobe(examine.getMicrobe());
        mme.setEc2(examine.getEc2());
        mme.setSource(examine.getSource());
        mme.setDbsource(examine.getDbsource());
        mme.setDate(examine.getDate());
        mme.setOrganism(examine.getOrganism());
        mme.setOrigin(examine.getOrigin());
        mme.setOperator(examine.getOperator());
        mme.setModifier(examine.getModifier());
        mme.setOperatedate(examine.getOperatedate());
        mme.setModifydate(examine.getModifydate());
        mme.setPdbid(examine.getPdbid());
        mme.setType(examine.getType());

        int roll = mmeMapper.updateByPrimaryKey(mme);
        if (roll <= 0){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.MARLBORO_FAILD);
            return result.toJSONString();
        }
        examineMapper.deleteByPrimaryKey(Integer.valueOf(id));
        result.put(Keys.DATA,new JSONObject());
        result.put(Keys.MSG,Errors.MARLBORO_SUCCESS);
        return result.toJSONString();
    }
    @RequestMapping(value = "/marlboro_batch", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String marlboro(){
        List<Examine> examines = examineMapper.selectAll();
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        if (examines == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.MARLBORO_BATCH_FAILD);
            return result.toJSONString();
        }
        for (Examine examine:examines){
            Mme mme = new Mme();
            mme.setId(examine.getId());
            mme.setTitle(examine.getTitle());
            mme.setTitle1(examine.getTitle1());
            mme.setTitle2(examine.getTitle2());
            mme.setTitle3(examine.getTitle3());
            mme.setTitle4(examine.getTitle4());
            mme.setAbstract1(examine.getAbstract1());
            mme.setAbstract2(examine.getAbstract2());
            mme.setAbstract3(examine.getAbstract3());
            mme.setAbstract4(examine.getAbstract4());
            mme.setAuthor1(examine.getAuthor1());
            mme.setAuthor2(examine.getAuthor2());
            mme.setAuthor3(examine.getAuthor3());
            mme.setAuthor4(examine.getAuthor4());
            mme.setJournal1(examine.getJournal1());
            mme.setJournal2(examine.getJournal2());
            mme.setJournal3(examine.getJournal3());
            mme.setJournal4(examine.getJournal4());
            mme.setPubmed1(examine.getPubmed1());
            mme.setPubmed2(examine.getPubmed2());
            mme.setPubmed3(examine.getPubmed3());
            mme.setPubmed4(examine.getPubmed4());

            mme.setCountry(examine.getCountry());
            mme.setLocus(examine.getLocus());
            mme.setMicrobe(examine.getMicrobe());
            mme.setEc2(examine.getEc2());
            mme.setSource(examine.getSource());
            mme.setDbsource(examine.getDbsource());
            mme.setDate(examine.getDate());
            mme.setOrganism(examine.getOrganism());
            mme.setOrigin(examine.getOrigin());
            mme.setOperator(examine.getOperator());
            mme.setModifier(examine.getModifier());
            mme.setOperatedate(examine.getOperatedate());
            mme.setModifydate(examine.getModifydate());
            mme.setPdbid(examine.getPdbid());
            mme.setType(examine.getType());

            mmeMapper.updateByPrimaryKey(mme);
            examineMapper.deleteByPrimaryKey(examine.getId());
        }
        result.put(Keys.DATA,new JSONObject());
        result.put(Keys.MSG,Errors.MARLBORO_BATCH_SUCCESS);
        return result.toJSONString();
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
