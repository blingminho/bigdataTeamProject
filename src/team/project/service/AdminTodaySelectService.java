package team.project.service;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import team.project.dao.AdminDao;
import team.project.dto.Admin;
import team.project.dto.Recommend;

public class AdminTodaySelectService {
	//반환값 admin ==> AdminTodayService.java 에서 호출하거나 ScheduleTrigger에서 호출함
	public Admin selectData() {
		System.out.println("2.AdminTodaySelectService selectData()");
		// 1. 파싱조건 - 오늘날짜로 시작과 끝날짜 지정
		Date date = new Date();
		SimpleDateFormat onlytoday = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat only_today = new SimpleDateFormat("yyyy-MM-dd");//하이픈 있는거
		//SimpleDateFormat nowtime = new SimpleDateFormat("hh:mm:ss");
		String simpletoday = onlytoday.format(date);
		String simple_today = only_today.format(date);
		//String now_time = nowtime.format(date);
		System.out.println("현재날짜 : " + simpletoday);// 20170730
		System.out.println("현재날짜 : " + simple_today);// 2017-07-30
		//System.out.println("시간 : " + now_time);// 11:18:35
		// 2 db받기
		// 2.1.1 오늘 등록된 동물수 api로부터 받아오고 admin객체에 set함
		Recommend rec = new Recommend();
		rec.setBgnde(simpletoday);
		rec.setEndde(simpletoday);
		rec.setUpkind("");
		rec.setUpr_cd("");
		JSONObject data = null;
		NoticePasing notice = new NoticePasing();
		Admin admin = new Admin();
		try {
			data = notice.pasing(rec);
			JSONObject data1 = (JSONObject) data.get("response");
			JSONObject data2 = (JSONObject) data1.get("body");
			int puppyTodayCount = 0;
			puppyTodayCount = (int) data2.get("totalCount");
			System.out.println("puppyTodayCount :"+ puppyTodayCount);//totalCount
				if (puppyTodayCount != 0)
					admin.setPuppyTodayCount(puppyTodayCount);
				System.out.println(admin);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//2.1.2 총 등록된 동물수 admin테이블에서 가져옴 //미완
		//int puppyTotalCount = AdminDao.getInstance().puppyTotalCountPrint();
		//int puppyTotalCount = admin.setPuppyTotalCount();
		
		// 2.2 오늘 및 총 가입자수 select하여 가져옴 regPrint(), regAllPrint()
		try {
			int regTodayCount=0;
			regTodayCount = AdminDao.getInstance().regPrint(simple_today);
			admin.setRegTodayCount(regTodayCount);
			System.out.println(admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2.3 오늘 및 총 방문자수 select하여 가져오고 admin객체에 넣음
		int visitTodayCount = AdminDao.getInstance().getVisitTodayCount(simple_today);
		int visitTotalCount = AdminDao.getInstance().getVisitTotalCount();
		// admin객체에 넣음
		admin.setVisitTodayCount(visitTodayCount);
		admin.setVisitTotalCount(visitTotalCount);
		System.out.println(admin);
		
		
		// 2.4 오늘 및 총 게시글수 select하여 가져오고 admin객체에 넣음
		// postPrint(),postAllPrint()
		try {
			int postTodayCount=0;
			int postTotalCount=0;
			postTodayCount = AdminDao.getInstance().postPrint(simple_today);
			postTotalCount = AdminDao.getInstance().postAllPrint();
			// admin객체에 넣음
			admin.setPostTodayCount(postTodayCount);
			admin.setPostTotalCount(postTotalCount);
			System.out.println(admin);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		// 3. admin테이블의 admin객체 select //미완 // 필요한가...?
		String date2 = null;
		try {
			date2 = AdminDao.getInstance().selectAdmin();
			System.out.println(date2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		return admin;
	}
	public List<Admin> selectDataJson(Admin admin) {
		List<Admin> list = new ArrayList<>();
		list.add(admin);
		return list;
	}
	
	
}
