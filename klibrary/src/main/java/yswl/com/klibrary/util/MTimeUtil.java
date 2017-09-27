package yswl.com.klibrary.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class MTimeUtil {
	public static final SimpleDateFormat YMD = new SimpleDateFormat("yyyy.MM.dd");
	public static final SimpleDateFormat Y_M_D = new SimpleDateFormat("yyyy-MM-dd");
	public static String getYMD(Date data) {
       if(data==null)return "0000.00.00";
       return YMD.format(data);
	}
	public static String getYMD() {
	       return YMD.format(new Date());
		}
	public static int  dataCompare(long data){
		  long offset = data - System.currentTimeMillis();
		  if(offset>0)return -1;
		  if(offset==0)return 0;
		  return 1;
	}
	public static boolean isOverdue(long data){
		 long offset = data - System.currentTimeMillis();
		 return offset<0;
	}
	public static String getNextDay(int day){
		 Date date = getNextDayD(day);
		 return Y_M_D.format(date);
	}
	public static long getNextDayL(int day){
		 Date date = getNextDayD(day);
		 return date.getTime();
	}
	public static Date getNextDayD(int day){
		 Calendar cl = Calendar.getInstance();
		 cl.add(Calendar.DAY_OF_MONTH,day);
		 Date date = cl.getTime();
		 return date;
	}
	public static long getNextYearDayL(int year){
		 Date date = getNextYearDay(year);
		 return  date.getTime();
	}
	public static Date getNextYearDay(int year){
		 Calendar cl = Calendar.getInstance();
		 cl.add(Calendar.YEAR,year);
		 return cl.getTime();
	}
	private static String getHMM(Date time) {
		SimpleDateFormat HMM = new SimpleDateFormat("HH:mm:ss");
		return HMM.format(time);
	}
	public static String get2YMDHMM(Date time){
		SimpleDateFormat HMM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return HMM.format(time);
	}
	public static String getYMDHMM(Date time){
		SimpleDateFormat HMM = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		return HMM.format(time);
	}
	public static String getYMD2(Date time){
		SimpleDateFormat HMM = new SimpleDateFormat("yyyy.MM.dd");
		return HMM.format(time);
	}
	public static String getY_M_D2(Date time){
		SimpleDateFormat HMM = new SimpleDateFormat("yyyy-MM-dd");
		return HMM.format(time);
	}
	public static String getY2_M_D2(Date time){
		SimpleDateFormat HMM = new SimpleDateFormat("yy-MM-dd");
		return HMM.format(time);
	}
	public static Calendar getCalendar(Date time){
		if(time==null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		return calendar;
	}

	public static String getCalendarDay(Calendar calendar) {
		  return getY_M_D2(calendar.getTime());
	}

	public static String getCalendarMiSecond(Calendar calendar) {
		int hour=calendar.get(Calendar.HOUR_OF_DAY);
		int mi= calendar.get(Calendar.MINUTE);
		int seconds= calendar.get(Calendar.SECOND);
		StringBuffer result=new StringBuffer();
		result.append(hour);
		result.append(":");
		if(mi<10){
			result.append("0");
		}
		result.append(mi);
		result.append(":");
		if(seconds<10){
			result.append("0");
		}
		result.append(seconds);
		return result.toString();
	}

	public static String getCurrTime(){
		return System.currentTimeMillis()+"";
	}

}
