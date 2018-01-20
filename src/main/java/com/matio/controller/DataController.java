package com.matio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.MmeMapper;
import com.matio.mapping.View_frontMapper;
import com.matio.pojo.Mme;
import com.matio.pojo.MmeCondition;
import com.matio.pojo.User;
import com.matio.pojo.View_front;
import com.matio.unit.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.ManagedMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by matioyoshitoki on 2017/11/20.
 */

@RestController
public class DataController {
    @Autowired
    MmeMapper mmeMapper;
    @Autowired
    View_frontMapper view_frontMapper;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @RequestMapping(value = "/manualInput", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String manualInput(
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
            @RequestParam(Keys.OPERATOR) String operator,
            @RequestParam(Keys.TYPE) String type,
            @RequestParam(Keys.EC2) String ec2,
            @RequestParam(Keys.DEEPSEA) String deepSea,
            @RequestParam(Keys.TEMPERATURE) String temperature,
            @RequestParam(Keys.PH) String ph,
            @RequestParam(Keys.ZONE) String zone,
            @RequestParam(Keys.COFACTORS) String cofactors,
            @RequestParam(Keys.INHIBITORS) String inhibitors
    ){
        JSONObject result;
        try {
            String ec1 = mmeMapper.selectEC1ByEC2(ec2);
            Date now = new Date();
            Mme mme = new Mme();
            mme.setAbstract1(abstract1);
            mme.setAbstract2(abstract2);
            mme.setAbstract3(abstract3);
            mme.setAbstract4(abstract4);
            mme.setTitle(title);
            mme.setTitle1(title1);
            mme.setTitle2(title2);
            mme.setTitle3(title3);
            mme.setTitle4(title4);
            mme.setJournal1(journal1);
            mme.setJournal2(journal2);
            mme.setJournal3(journal3);
            mme.setJournal4(journal4);
            mme.setPubmed1(pubmed1);
            mme.setPubmed2(pubmed2);
            mme.setPubmed3(pubmed3);
            mme.setPubmed4(pubmed4);
            mme.setAuthor1(author1);
            mme.setAuthor2(author2);
            mme.setAuthor3(author3);
            mme.setAuthor4(author4);
            mme.setLocus(locus);
            mme.setOrganism(organsim);
            mme.setOrigin(origin);
            mme.setPdbid(pdbid);
            mme.setDate(date);
            mme.setDbsource(dbsource);
            mme.setSource(source);
            mme.setCountry(country);
            mme.setType(type);
            mme.setEc1(ec1);
            mme.setEc2(ec2);
            mme.setDeepsea(deepSea);
            mme.setTemperature(temperature);
            mme.setPh(ph);
            mme.setZone(zone);
            mme.setCofactors(cofactors);
            mme.setInhibitors(inhibitors);
            mme.setOperatedate(simpleDateFormat.format(now));
            mme.setModifydate(simpleDateFormat.format(now));
            mme.setIsModified("0");
            try {
                mme.setOperator(operator.getBytes("utf8"));
                mme.setModifier(operator.getBytes("utf8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (mmeMapper.insert(mme) > 0){
                result = JsonUtil.fromErrors(Errors.SUCCESS);
                result.put(Keys.MSG,Errors.MIFSUCCESS);
                result.put(Keys.DATA,new JSONObject());
            }else {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG,Errors.MIFAILD);
                result.put(Keys.DATA,new JSONObject());
            }
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.SYSTEM_ERROR);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/getData", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getData(
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
                    try {
                        condition.setOperator(param.getBytes("utf8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
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
                    condition.setStartDate_op(startDate);
                    condition.setEndDate_op(endDate);
                    break;
                case "7":
                    startDate += " 00:00:00";
                    endDate += " 23:59:59";
                    condition.setStartDate_md(startDate);
                    condition.setEndDate_md(endDate);
                    break;
                default:
                    return "";
            }
            List<Mme> mmes = mmeMapper.selectByCondition(condition);
            JSONArray data = new JSONArray();
            for (Mme mme:mmes){
                JSONObject buff = new JSONObject();
                buff.put(Keys.MMEID, mme.getId());
                buff.put(Keys.TITLE,mme.getTitle());
                buff.put(Keys.TITLE1,mme.getTitle1());
                buff.put(Keys.TITLE2,mme.getTitle2());
                buff.put(Keys.TITLE3,mme.getTitle3());
                buff.put(Keys.TITLE4,mme.getTitle4());
                buff.put(Keys.ABSTRACT1,mme.getAbstract1());
                buff.put(Keys.ABSTRACT2,mme.getAbstract2());
                buff.put(Keys.ABSTRACT3,mme.getAbstract3());
                buff.put(Keys.ABSTRACT4,mme.getAbstract4());
                buff.put(Keys.AUTHOR1,mme.getAuthor1());
                buff.put(Keys.AUTHOR2,mme.getAuthor2());
                buff.put(Keys.AUTHOR3,mme.getAuthor3());
                buff.put(Keys.AUTHOR4,mme.getAuthor4());
                buff.put(Keys.JOURNAL1,mme.getJournal1());
                buff.put(Keys.JOURNAL2,mme.getJournal2());
                buff.put(Keys.JOURNAL3,mme.getJournal3());
                buff.put(Keys.JOURNAL4,mme.getJournal4());
                buff.put(Keys.PUBMED1,mme.getPubmed1());
                buff.put(Keys.PUBMED2,mme.getPubmed2());
                buff.put(Keys.PUBMED3,mme.getPubmed3());
                buff.put(Keys.PUBMED4,mme.getPubmed4());

                buff.put(Keys.COUNTRY,mme.getCountry());
                buff.put(Keys.LOCUS,mme.getLocus());
                buff.put(Keys.MICROBE,mme.getMicrobe());
                buff.put(Keys.EC1,mme.getEc1());
                buff.put(Keys.EC2,mme.getEc2());
                buff.put(Keys.SOURCE,mme.getSource());
                buff.put(Keys.DBSOURCE,mme.getDbsource());
                buff.put(Keys.DATE,mme.getDate());
                buff.put(Keys.ORGANISM,mme.getOrganism());
                buff.put(Keys.DEEPSEA,mme.getDeepsea());
                buff.put(Keys.TEMPERATURE,mme.getTemperature());
                buff.put(Keys.PH,mme.getPh());
                buff.put(Keys.ZONE,mme.getZone());
                buff.put(Keys.COFACTORS,mme.getCofactors());
                buff.put(Keys.INHIBITORS,mme.getInhibitors());
                buff.put(Keys.ORIGIN,mme.getOrigin());
                buff.put(Keys.OPERATEDATE,mme.getOperatedate());
                buff.put(Keys.MODIFYDATE,mme.getModifydate());
                buff.put(Keys.PDBID,mme.getPdbid());
                buff.put(Keys.TYPE,mme.getType());
                buff.put(Keys.ISMODIFIED,mme.getIsModified());
                try {
                    buff.put(Keys.OPERATOR,new String(mme.getOperator(),"utf8"));
                    buff.put(Keys.MODIFIER,new String(mme.getModifier(),"utf8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                data.add(buff);
            }
            int size = mmeMapper.selectCountByCondition(condition);
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.DATA,data);
            result.put(Keys.MSG,"");
            result.put(Keys.COUNT,size);
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.SYSTEM_ERROR);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/deleteData", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String deleteData(
            @RequestParam(Keys.ID) String id
    ){
        JSONObject result ;
        try {
            if(mmeMapper.deleteByPrimaryKey(Integer.valueOf(id)) <= 0) {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG,Errors.DELETEDATA_FAILD);
                result.put(Keys.DATA,new JSONObject());
                return result.toJSONString();
            }
            result = JsonUtil.fromErrors(Errors.SUCCESS);
            result.put(Keys.MSG,Errors.DELETEDATA_SUCCESS);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.SYSTEM_ERROR);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/modifyInput", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String modifyInput(
            @RequestParam(Keys.MMEID) String mmeId,
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
            @RequestParam(Keys.OPERATOR) String operator,
            @RequestParam(Keys.TYPE) String type,
            @RequestParam(Keys.EC2) String ec2,
            @RequestParam(Keys.DEEPSEA) String deepSea,
            @RequestParam(Keys.TEMPERATURE) String temperature,
            @RequestParam(Keys.PH) String ph,
            @RequestParam(Keys.ZONE) String zone,
            @RequestParam(Keys.COFACTORS) String cofactors,
            @RequestParam(Keys.INHIBITORS) String inhibitors
    ) {
        JSONObject result;
        try {
            String ec1 = mmeMapper.selectEC1ByEC2(ec2);
            Date now = new Date();
            Mme mme = new Mme();
            mme.setId(Integer.valueOf(mmeId));
            mme.setAbstract1(abstract1);
            mme.setAbstract2(abstract2);
            mme.setAbstract3(abstract3);
            mme.setAbstract4(abstract4);
            mme.setTitle(title);
            mme.setTitle1(title1);
            mme.setTitle2(title2);
            mme.setTitle3(title3);
            mme.setTitle4(title4);
            mme.setJournal1(journal1);
            mme.setJournal2(journal2);
            mme.setJournal3(journal3);
            mme.setJournal4(journal4);
            mme.setPubmed1(pubmed1);
            mme.setPubmed2(pubmed2);
            mme.setPubmed3(pubmed3);
            mme.setPubmed4(pubmed4);
            mme.setAuthor1(author1);
            mme.setAuthor2(author2);
            mme.setAuthor3(author3);
            mme.setAuthor4(author4);
            mme.setLocus(locus);
            mme.setOrganism(organsim);
            mme.setOrigin(origin);
            mme.setPdbid(pdbid);
            mme.setDate(date);
            mme.setDbsource(dbsource);
            mme.setSource(source);
            mme.setCountry(country);
            mme.setType(type);
            mme.setEc1(ec1);
            mme.setEc2(ec2);
            mme.setDeepsea(deepSea);
            mme.setTemperature(temperature);
            mme.setPh(ph);
            mme.setZone(zone);
            mme.setCofactors(cofactors);
            mme.setInhibitors(inhibitors);
            mme.setModifydate(simpleDateFormat.format(now));
            mme.setIsModified("0");
            try {
                mme.setModifier(operator.getBytes("utf8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            if (mmeMapper.updateByPrimaryKeyWithBLOBs(mme) > 0){
                result = JsonUtil.fromErrors(Errors.SUCCESS);
                result.put(Keys.MSG,Errors.MODIFY_DATA_SUCCESS);
                result.put(Keys.DATA,new JSONObject());
            }else {
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.MSG,Errors.MODIFY_DATA_FAILD);
                result.put(Keys.DATA,new JSONObject());
            }
            return result.toJSONString();
        } catch(Exception e) {
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.SYSTEM_ERROR);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
    }

    @RequestMapping(value = "/getFrontList", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getFrontList(
            @RequestParam(Keys.EC1) String ec1,
            @RequestParam(Keys.PREFIX) String prefix,
            @RequestParam(Keys.STARTPOS) String startPos,
            @RequestParam(Keys.NUMBERPERPAGE) String numberPerPage
    ){
        MmeCondition mmeCondition = new MmeCondition();
        int startPos_int = Integer.valueOf(startPos);
        int numberPerPage_int = Integer.valueOf(numberPerPage);
        int startSize = (startPos_int - 1) * numberPerPage_int;
        int endSize = (startPos_int) * numberPerPage_int ;
        mmeCondition.setStartSize(startSize);
        mmeCondition.setEndSize(endSize);
        mmeCondition.setEc2(prefix.toLowerCase());
        mmeCondition.setEc1(ec1);
        List<View_front> view_fronts = view_frontMapper.selectByFuzzyEC2(mmeCondition);
        int count = view_frontMapper.selectCountByFuzzyEC2(mmeCondition);

        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        JSONArray data = new JSONArray();
        if (view_fronts == null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.MSG,Errors.GETDATEFAILD);
            result.put(Keys.DATA,new JSONObject());
            return result.toJSONString();
        }
        for (View_front view_front:view_fronts){
            JSONObject buff = new JSONObject();
            buff.put(Keys.TITLE,view_front.getTitle());
            buff.put(Keys.EC2,view_front.getEc2());
            buff.put(Keys.LOCUS,view_front.getLocus());
            data.add(buff);
        }
        result.put(Keys.DATA,data);
        result.put(Keys.MSG,"");
        result.put(Keys.COUNT, count);
        return result.toJSONString();
    }
}
