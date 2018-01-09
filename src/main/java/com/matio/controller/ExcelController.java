package com.matio.controller;

import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.MmeMapper;
import com.matio.pojo.Mme;
import com.matio.services.impl.MmeService;
import com.matio.services.intf.IMmeService;
import com.matio.tools.ReadExcelUtils;
import com.matio.unit.JsonUtil;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by matioyoshitoki on 2017/11/29.
 */
@RestController
public class ExcelController {

    @Autowired
    MmeMapper mmeMapper;
    @Autowired
    IMmeService mmeService;

    @RequestMapping(value = "/excel_operate", method = RequestMethod.POST)
    public String excel_operate(
            @RequestParam(Keys.FILE) MultipartFile file,
            @RequestParam(Keys.OPERATOR) String operator
    ) {
        JSONObject result = JsonUtil.fromErrors(Errors.SUCCESS);
        String file_name = file.getOriginalFilename();
        System.out.println(file.getOriginalFilename());
        ReadExcelUtils excelReader = null;
        try {
            excelReader = new ReadExcelUtils(file_name,file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Integer, Map<Integer,Object>> map = null;
        try {
            map = excelReader.readExcelContent();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<String> titles = new ArrayList<>();
        List<List<String>> content = new ArrayList<>();
        List<String> data = new ArrayList<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < map.size(); i++) {
            List<String> tmp = new ArrayList<>();
            for (int j=0;j<map.get(i).size();j++){
                if (i==0) {
                    titles.add((String) map.get(i).get(j));
                }else {
                    tmp.add((String) map.get(i).get(j));
                }
            }
            if (i!=0) {
                content.add(tmp);
            }
        }
//        System.out.println(titles);
        for (int index = 0;index<content.size();index++) {
            List<String> tmp = content.get(index);
            Mme mme = new Mme();
            for (int i = 0; i < titles.size(); i++) {
                switch (titles.get(i)){
                    case Keys.TITLE:
                        mme.setTitle(tmp.get(i));
                        break;
                    case Keys.TITLE1:
                        mme.setTitle1(tmp.get(i));
                        break;
                    case Keys.TITLE2:
                        mme.setTitle2(tmp.get(i));
                        break;
                    case Keys.TITLE3:
                        mme.setTitle3(tmp.get(i));
                        break;
                    case Keys.TITLE4:
                        mme.setTitle4(tmp.get(i));
                        break;
                    case Keys.ABSTRACT1:
                        mme.setAbstract1(tmp.get(i));
                        break;
                    case Keys.ABSTRACT2:
                        mme.setAbstract2(tmp.get(i));
                        break;
                    case Keys.ABSTRACT3:
                        mme.setAbstract3(tmp.get(i));
                        break;
                    case Keys.ABSTRACT4:
                        mme.setAbstract4(tmp.get(i));
                        break;
                    case Keys.AUTHOR1:
                        mme.setAuthor1(tmp.get(i));
                        break;
                    case Keys.AUTHOR2:
                        mme.setAuthor2(tmp.get(i));
                        break;
                    case Keys.AUTHOR3:
                        mme.setAuthor3(tmp.get(i));
                        break;
                    case Keys.AUTHOR4:
                        mme.setAuthor4(tmp.get(i));
                        break;
                    case Keys.JOURNAL1:
                        mme.setJournal1(tmp.get(i));
                        break;
                    case Keys.JOURNAL2:
                        mme.setJournal2(tmp.get(i));
                        break;
                    case Keys.JOURNAL3:
                        mme.setJournal3(tmp.get(i));
                        break;
                    case Keys.JOURNAL4:
                        mme.setJournal4(tmp.get(i));
                        break;
                    case Keys.COUNTRY:
                        mme.setCountry(tmp.get(i));
                        break;
                    case Keys.EC1:
                        mme.setEc1(tmp.get(i));
                        break;
                    case Keys.EC2:
                        mme.setEc2(tmp.get(i));
                        break;
                    case Keys.TYPE:
                        mme.setType(tmp.get(i));
                        break;
                    case Keys.LOCUS:
                        mme.setLocus(tmp.get(i));
                        break;
                    case Keys.PDBID:
                        mme.setPdbid(tmp.get(i));
                        break;
                    case Keys.DBSOURCE:
                        mme.setDbsource(tmp.get(i));
                        break;
                    case Keys.SOURCE:
                        mme.setSource(tmp.get(i));
                        break;
                    case Keys.MICROBE:
                        mme.setMicrobe(tmp.get(i));
                        break;
                    case Keys.ORGANISM:
                        mme.setOrganism(tmp.get(i));
                        break;
                    case Keys.ORIGIN:
                        mme.setOrigin(tmp.get(i));
                        break;
                    case Keys.DATE:
                        mme.setDate(tmp.get(i));
                        break;
                    case Keys.DEEPSEA:
                        mme.setDeepsea(tmp.get(i));
                        break;
                    case Keys.TEMPERATURE:
                        mme.setTemperature(tmp.get(i));
                        break;
                    case Keys.PH:
                        mme.setPh(tmp.get(i));
                        break;
                    case Keys.ZONE:
                        mme.setZone(tmp.get(i));
                        break;
                    case Keys.COFACTORS:
                        mme.setCofactors(tmp.get(i));
                        break;
                    case Keys.INHIBITORS:
                        mme.setInhibitors(tmp.get(i));
                        break;
                    default:
                        break;
                }

            }
            System.out.print(mme.getLocus());
            String locus = mmeService.selectByLocus(mme.getLocus());
            if (locus != null){
                data.add(mme.getLocus());
                continue;
            }
            Date now = new Date();

            mme.setOperatedate(simpleDateFormat.format(now));
            try {
                mme.setModifier(operator.getBytes("utf8"));
                mme.setOperator(operator.getBytes("utf8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            mme.setModifydate(simpleDateFormat.format(now));
            int flag = mmeMapper.insert(mme);
            if (flag <= 0){
                data.add(mme.getLocus());
            }
        }
        result.put(Keys.DATA,data);
        result.put(Keys.MSG,"");
        result.put(Keys.COUNT,map.size()-1);
        return result.toJSONString();
    }
}
