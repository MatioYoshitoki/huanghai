package com.matio.tools;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by matioyoshitoki on 2017/8/3.
 */
public class Tools {
    public static String getSomeDayBefore(String now,int days) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.setTime(df.parse(now));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - days);
        return df.format(calendar.getTime());
    }

    public static String getOneDayBefore(String now) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.setTime(df.parse(now));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        return df.format(calendar.getTime());
    }

    public static String getOneDayLater(String now) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        calendar.setTime(df.parse(now));
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        return df.format(calendar.getTime());
    }

    /**
     * 生成sessionid
     * @author chenke
     * @return sessionid
     */
    public static String generateSessionId(int length){
        String uuid = UUID.randomUUID().toString();
        String sessionId = uuid.replace("-", "").toUpperCase();

        return sessionId.substring(0,length);
    }

    public static boolean isCrawlerStarted() throws IOException {
        String result = runProcess("jps -l");
        return result.contains("huanghai_catch");
    }

    public static void startCrawler() throws IOException {
        runProcess("/root/catch/startUp.sh");
    }

    public static boolean stopCrawler() throws IOException {
        File pid_file = new File("root/catch/pid");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(pid_file));
        String tmp ;
        if ((tmp = bufferedReader.readLine())!=null){
            runProcess("kill "+tmp);
            return true;
        }else {
            return false;
        }
    }

    public static String runProcess(String exec) throws IOException {
        Process process = Runtime.getRuntime().exec(exec);
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
        InputStreamReader ir=new InputStreamReader(process.getInputStream());
        LineNumberReader input = new LineNumberReader (ir);

        String line;
        StringBuffer result = new StringBuffer();
        while ((line = input.readLine ()) != null){
            result.append(line+"\n");
        }
        return result.toString();
    }


}
