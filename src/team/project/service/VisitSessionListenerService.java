package team.project.service;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import team.project.dao.AdminDao;
//세션이 새로 생성되면 총방문자수 증가
public class VisitSessionListenerService implements HttpSessionListener {
	
	////세션이 새로 생성될 경우 실행됨 즉 한명이라도 접속할경우 실행되는 서비스
	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionCreated(se);
		System.out.println("sessionCreated()");
		if(se.getSession().isNew()){// 세션이 새로 생성되면 visitcount()와 Schedule을 실행한다.
			visitcount();//총 방문자수 증가시킴 
			
			//new Schedule().main() : 스케쥴 클래스의 메인문 객체 생성
			//==> 즉 주기적으로 admin테이블에 입력가능해짐
			new Schedule().main();
		}
	}
	
    ////세션이 끝날 경우 실행됨 즉 한명이라도 접속해제 할경우 실행되는 서비스
	//세션이 계속 죽어있어도 new Schedule().main(); 을 실행함
	//--------------------------------------------------------------------------
	//***즉 사이트가 한번이라도 켜지거나 꺼지면 주기적으로 Schedule을 실행함***
	//--------------------------------------------------------------------------
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		HttpSessionListener.super.sessionDestroyed(se);
		System.out.println("sessionDestroyed()");
		//new Schedule().main();
	}

	//총방문자수 증가
	//AdminDao의 setVisitTotalCount(simple_today,timetoday) 메소드를 실행 => 매개변수는 오늘 날짜, 날짜시간 이다.
	public void visitcount() {
        try {
        	Date date = new Date();
        	SimpleDateFormat only_today = new SimpleDateFormat("yyyy-MM-dd");// 하이픈있음
    		SimpleDateFormat time_today = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    		String simple_today = only_today.format(date);
    		String timetoday = time_today.format(date);
        	AdminDao.getInstance().setVisitTotalCount(simple_today,timetoday);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
