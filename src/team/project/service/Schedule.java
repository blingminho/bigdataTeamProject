package team.project.service;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;

//시간 정해서 일정시간마다 실행시킴 => ScheduleTrigger.java 를 주기적으로 실행함
public class Schedule {
	public static void main() {
		Timer timer = new Timer();
		ScheduleTrigger scheduleTrigger = new ScheduleTrigger();
		GregorianCalendar startDate = new GregorianCalendar();
		startDate.set(2017,Calendar.AUGUST,3,14,55);//시작시간 설정//2017년,8월,2일,14시,54분
		System.out.println("Schedule");
		//딜레이 시간 설정
		long dailybatch = 24*60*60*1000; //24시간마다
		long testbatch = 20000;//20초뒤
		long testbatch2 = 1*60*60*1000;//1시간마다
		//시작시간을 기준으로 딜레이 시간이 지나면 startDate실행
		timer.scheduleAtFixedRate(scheduleTrigger,startDate.getTime(),testbatch2);
		//scheduleTrigger실행,시작시간,딜레이시간
	}
}
