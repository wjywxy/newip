package com.huayin.smack;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * @ClassName:  DateUtil
 * @Description:TODO(这里用一句话描述这个类的作用)
 * @author:     wangjinyong
 * @date:       2017年8月2日 下午3:31:46
 * Copyright (c) 2017, Huayin. All rights reserved
 */
public class DateUtil {
	
    /**
     * 掉此方法输入所要转换的时间输入例如（"2014-06-14 16:09:00"）返回时间戳
     * 
     * @param time
     * @return
     */
    public static String dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }
 
    public static String getTimestamp(String time, String type) {
        SimpleDateFormat sdr = new SimpleDateFormat(type, Locale.CHINA);
        Date date;
        String times = null;
        try {
            date = sdr.parse(time);
            long l = date.getTime();
            String stf = String.valueOf(l);
            times = stf.substring(0, 10);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return times;
    }
 
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分00秒"）
     * 
     * @param time
     * @return
     */
    public String times(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
 
    }
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014年06月14日16时09分"）
     * 
     * @param time
     * @return
     */
    public String timet(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
         
    }
 
    // 调用此方法输入所要转换的时间戳例如（1402733340）输出（"2014年06月14日16时09分00秒"）
    public static String times(long timeStamp) {
        SimpleDateFormat sdr = new SimpleDateFormat("MM月dd日  #  HH:mm");
        return sdr.format(new Date(timeStamp)).replaceAll("#",
                getWeek(timeStamp));
 
    }
 
    private static String getWeek(long timeStamp) {
        int mydate = 0;
        String week = null;
        Calendar cd = Calendar.getInstance();
        cd.setTime(new Date(timeStamp));
        mydate = cd.get(Calendar.DAY_OF_WEEK);
        // 获取指定日期转换成星期几
        if (mydate == 1) {
            week = "周日";
        } else if (mydate == 2) {
            week = "周一";
        } else if (mydate == 3) {
            week = "周二";
        } else if (mydate == 4) {
            week = "周三";
        } else if (mydate == 5) {
            week = "周四";
        } else if (mydate == 6) {
            week = "周五";
        } else if (mydate == 7) {
            week = "周六";
        }
        return week;
    }
 
    // 并用分割符把时间分成时间数组
    /**
     * 调用此方法输入所要转换的时间戳输入例如（1402733340）输出（"2014-06-14 16:09:00"）
     * 
     * @param time
     * @return
     */
    public static String timesOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        return times;
 
    }
    
    public static String timestampToDate(String times, String type) {
    	SimpleDateFormat sdr = new SimpleDateFormat(type,Locale.CHINA);
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(times);
        int i = Integer.parseInt(times);
        String dateStr = sdr.format(new Date(i * 1000L));
        return dateStr;
    }
 
    /**
     * 并用分割符把时间分成时间数组
     * 
     * @param time
     * @return
     */
    public static String[] timestamp(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        @SuppressWarnings("unused")
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        String[] fenge = times.split("[年月日时分秒]");
        return fenge;
    }
 
    /**
     * 根据传递的类型格式化时间
     * 
     * @param str
     * @param type
     *            例如：yy-MM-dd
     * @return
     */
    public static String getDateTimeByMillisecond(String str, String type) {
 
        Date date = new Date(Long.valueOf(str));
 
        SimpleDateFormat format = new SimpleDateFormat(type);
 
        String time = format.format(date);
 
        return time;
    }
 
    /**
     * 分割符把时间分成时间数组
     * 
     * @param time
     * @return
     */
    public String[] division(String time) {
 
        String[] fenge = time.split("[年月日时分秒]");
 
        return fenge;
 
    }
 
    /**
     * 输入时间戳变星期
     * 
     * @param time
     * @return
     */
    public static String changeweek(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        Date date = null;
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(times);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
 
    }
 
    /**
     * 获取日期和星期　例如：２０１４－１１－１３　１１:００　星期一
     * 
     * @param time
     * @param type
     * @return
     */
    public static String getDateAndWeek(String time, String type) {
        return getDateTimeByMillisecond(time + "000", type) + "  "
                + changeweekOne(time);
    }
 
    /**
     * 输入时间戳变星期
     * 
     * @param time
     * @return
     */
    public static String changeweekOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        long lcc = Long.valueOf(time);
        int i = Integer.parseInt(time);
        String times = sdr.format(new Date(i * 1000L));
        Date date = null;
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(times);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
 
    }
 
    /**
     * 获取当前时间
     * 
     * @return
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日  HH时mm分");
        return sdf.format(new java.util.Date());
    }
 
    /**
     * 输入日期如（2014年06月14日16时09分00秒）返回（星期数）
     * 
     * @param time
     * @return
     */
    public String week(String time) {
        Date date = null;
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(time);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
    }
 
    /**
     * 输入日期如（2014-06-14-16-09-00）返回（星期数）
     * 
     * @param time
     * @return
     */
    public String weekOne(String time) {
        Date date = null;
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        int mydate = 0;
        String week = null;
        try {
            date = sdr.parse(time);
            Calendar cd = Calendar.getInstance();
            cd.setTime(date);
            mydate = cd.get(Calendar.DAY_OF_WEEK);
            // 获取指定日期转换成星期几
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (mydate == 1) {
            week = "星期日";
        } else if (mydate == 2) {
            week = "星期一";
        } else if (mydate == 3) {
            week = "星期二";
        } else if (mydate == 4) {
            week = "星期三";
        } else if (mydate == 5) {
            week = "星期四";
        } else if (mydate == 6) {
            week = "星期五";
        } else if (mydate == 7) {
            week = "星期六";
        }
        return week;
    }
	
	
	
	//将时间date转成秒数
	public static Long dateToTimes(String str){
		long times = 0;
		try {
			//String time = "2011/07/29 14:50:11";
			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str);
			times = date.getTime() / 1000;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return times;
	}
	
	//时间点转成时间戳（HH*60*60+mm*60+ss）
	public static Long timesToTimestamp(String str){
		long times = 0;
		String[] fenge = str.split(":");
		times = Integer.parseInt(fenge[0])*60*60 + Integer.parseInt(fenge[1])*60 + Integer.parseInt(fenge[2]);
		return times;
	}
	
	//时间戳转时间点
	public static String timestampToTimes(int timestamps) {
		if (timestamps > 86400 || timestamps < 0) {
			return "00:00:00";
		}
		String hh = timestamps/60/60 > 10 ? String.valueOf(timestamps/60/60):"0"+timestamps/60/60;
		String mm = (timestamps - Integer.parseInt(hh)*60*60)/60 > 10 ? String.valueOf((timestamps - Integer.parseInt(hh)*60*60)/60) : "0"+(timestamps - Integer.parseInt(hh)*60*60)/60;
		String ss = (timestamps - Integer.parseInt(hh)*60*60 - Integer.parseInt(mm)*60) > 10 ? String.valueOf((timestamps - Integer.parseInt(hh)*60*60 - Integer.parseInt(mm)*60)) : "0"+(timestamps - Integer.parseInt(hh)*60*60 - Integer.parseInt(mm)*60);
	    return hh+":"+mm+":"+ss;
	}
	
	//获取随机数
	public static List<Integer> getRanDom(Integer count){
		java.util.Random ran=new java.util.Random();
		java.util.List<Integer> list=new ArrayList<Integer>();
		while(list.size() < 10)
		{
			int n=ran.nextInt(10);
			if(!list.contains(n)){
				list.add(n);
			}
		}
		return list;
		/*for(int i:list)//foreach循环取值
		System.out.println(i);*/
	}
	/** 
	 * @Title:        main 
	 * @Description:  TODO(这里用一句话描述这个方法的作用) 
	 * @param:        @param args    
	 * @return:       void    
	 * @throws 
	 * @author        wangjinyong
	 * @Date          2017年8月2日 下午3:31:46 
	 */
	public static void main(String[] args) {
		//System.out.println(getTimestamp("2017-08-19","yyyy-MM-dd"));  //1497715200
		//System.out.println(timestampToDate("1668693316","yyyy-MM-dd"));
		//System.out.println(timesToTimestamp("23:01:00"));
		//System.out.println(timestampToTimes(-1));
		System.out.println(System.currentTimeMillis());  //1503127788070
														 //1503127801986

	}

}
