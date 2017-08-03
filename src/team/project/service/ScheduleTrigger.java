package team.project.service;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import team.project.dao.AdminDao;
import team.project.dto.Admin;


public class ScheduleTrigger extends TimerTask {
	@Override
	public void run() {
		System.out.println("ScheduleTrigger run()");
		//날짜와 시간은 안에서 만들고
		Date date = new Date();
		SimpleDateFormat only_today = new SimpleDateFormat("yyyy-MM-dd ");//하이픈 있는거
		SimpleDateFormat only_time = new SimpleDateFormat("hh:mm:ss");
		String simple_today = only_today.format(date);
		String simple_time = only_time.format(date);
		
		//admin객체는 AdminTodaySelectService()의 selectData()메소드에서 받아옴
		Admin admin = new AdminTodaySelectService().selectData();
		
		try {
			//admintest테이블에 값 넣는 AdminDao의 insertAdmin 메소드 실행
			//insertAdmin 메소드의 매개변수는 오늘날짜, 지금시간, admin객체(DTO)
			int result = AdminDao.getInstance().insertAdmin(simple_today,simple_time, admin);
			System.out.println("Admin 테이블에 " + result + "행이 입력되었습니다.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
