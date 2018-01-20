package com.matio.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.matio.constraints.Errors;
import com.matio.constraints.Keys;
import com.matio.mapping.View_spider_recordMapper;
import com.matio.pojo.View_spider_record;
import com.matio.tools.Tools;
import com.matio.unit.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by matioyoshitoki on 2018/1/2.
 */
@RestController
public class CrawlerController {
    @Autowired
    View_spider_recordMapper view_spider_recordMapper;

    @RequestMapping(value = "/getCrawlerNote", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String getCrawlerNote(){
        List<View_spider_record> records = view_spider_recordMapper.selectAll();
        JSONObject result ;
        if (records==null){
            result = JsonUtil.fromErrors(Errors.FAILD);
            result.put(Keys.DATA,new JSONArray());
            result.put(Keys.MSG,"");
            return result.toJSONString();
        }
        result = JsonUtil.fromErrors(Errors.SUCCESS);
        int size = records.size()>10?10:records.size();
        JSONArray data = new JSONArray();
        for (int i=0;i<size;i++){
            JSONObject tmp = new JSONObject();
            tmp.put(Keys.NUMBER,records.get(i).getNumber());
            tmp.put(Keys.OPERATEDATE,records.get(i).getOperatedate());
            data.add(tmp);
        }
        result.put(Keys.DATA,data);
        result.put(Keys.MSG,"");
        return result.toJSONString();
    }

    @RequestMapping(value = "/startCrawler", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String startCrawler(){
        JSONObject result = JsonUtil.fromErrors(Errors.FAILD);
        result.put(Keys.MSG,Errors.START_CRAWLER_FAILD);
        result.put(Keys.DATA,new JSONArray());
        try {
            boolean is_started = Tools.isCrawlerStarted();
            if (is_started){
                return result.toJSONString();
            } else {
                result = JsonUtil.fromErrors(Errors.SUCCESS);
                result.put(Keys.MSG,Errors.START_CRAWLER_SUCCESS);
                result.put(Keys.DATA,new JSONArray());
                Tools.startCrawler();
                return result.toJSONString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toJSONString();
    }

    @RequestMapping(value = "/stopCrawler", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String stopCrawler(){
        JSONObject result = JsonUtil.fromErrors(Errors.FAILD);
        result.put(Keys.MSG,Errors.STOP_CRAWLER_FAILD);
        result.put(Keys.DATA,new JSONArray());
        try {
            boolean is_started = Tools.isCrawlerStarted();
            if (!is_started){
                return result.toJSONString();
            }else {
                boolean success = Tools.stopCrawler();
                if (success){
                    result = JsonUtil.fromErrors(Errors.SUCCESS);
                    result.put(Keys.MSG,Errors.STOP_CRAWLER_SUCCESS);
                    result.put(Keys.DATA,new JSONArray());
                }
                return result.toJSONString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toJSONString();
    }

    @RequestMapping(value = "/setCrawlerTime", method = RequestMethod.POST , produces="text/json;charset=UTF-8")
    public String setCrawlerTime(
        @RequestParam(Keys.TIME) String time
    ){
        JSONObject result = JsonUtil.fromErrors(Errors.FAILD);
        result.put(Keys.MSG,Errors.SET_CRAWLER_TIME_FAILD);
        result.put(Keys.DATA,new JSONArray());
        File job = new File("/root/catch/job.properties");
        OutputStreamWriter osw = null;
        BufferedWriter bw ;
        try {
            osw = new OutputStreamWriter(new FileOutputStream(job));
            bw = new BufferedWriter(osw);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return result.toJSONString();
        }

        String content ="# every day, 2 o`clock\n"+
                "AllCatchService.job=com.matio.huanghaicatch.service.AllCatchService\n"+
                "AllCatchService.cron="+time+"\n"+
                "AllCatchService.enable=true\n\n"+
                "# every hour\n"+
                "CookieCatchService.job=com.matio.huanghaicatch.service.CookieCatchService\n"+
                "CookieCatchService.cron=0 * * * *\n"+
                "CookieCatchService.enable=true";
        try {
            bw.write(content);
            bw.flush();
            bw.close();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
            return result.toJSONString();
        }
        result = JsonUtil.fromErrors(Errors.SUCCESS);
        result.put(Keys.MSG,Errors.SET_CRAWLER_TIME_SUCCESS);
        result.put(Keys.DATA,new JSONArray());
        try {
            boolean is_started = Tools.isCrawlerStarted();
            if (is_started){
                Tools.stopCrawler();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Tools.startCrawler();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result.toJSONString();
    }
}
