package com.matio.services.impl;

import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.ExamineMapper;
import com.matio.mapping.MmeMapper;
import com.matio.pojo.Examine;
import com.matio.pojo.Mme;
import com.matio.services.intf.IMmeService;
import com.matio.unit.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by matioyoshitoki on 2017/11/29.
 */
@Service("mmeService")
public class MmeService implements IMmeService {

    @Autowired
    MmeMapper mmeMapper;
    @Autowired
    ExamineMapper examineMapper;

    @Override
    @Transactional(rollbackFor={RuntimeException.class, Exception.class})
    public String marlboro(Examine examine) {
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
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
        mme.setModifier(examine.getModifier());
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
        if (examineMapper.deleteByPrimaryKey(examine.getId()) <= 0){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONObject());
            result.put(Keys.MSG,Errors.MARLBORO_FAILD);

            return result.toJSONString();
        }
        result.put(Keys.DATA,new JSONObject());
        result.put(Keys.MSG,Errors.MARLBORO_SUCCESS);
        return result.toJSONString();
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public String marlboro_batch(List<Examine> examines) {
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
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
            mme.setModifier(examine.getModifier());
            mme.setModifydate(examine.getModifydate());
            mme.setPdbid(examine.getPdbid());
            mme.setType(examine.getType());

            if (mmeMapper.updateByPrimaryKey(mme)<=0){
                result = JsonUtil.fromErrors(Errors.FAILD);
                result.put(Keys.DATA,new JSONObject());
                result.put(Keys.MSG,Errors.MARLBORO_BATCH_FAILD);
                return result.toJSONString();
            }else {
                examineMapper.deleteByPrimaryKey(examine.getId());
            }
        }

//        session.commit();
        result.put(Keys.DATA,new JSONObject());
        result.put(Keys.MSG,Errors.MARLBORO_BATCH_SUCCESS);
        return result.toJSONString();
    }
}
