package team.project.service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import team.project.dto.Admin;


public class AdminTodayService_ajax implements UserService {
	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2.AdminTodayService");
		List<Admin> list = new ArrayList<>();
		AdminTodaySelectService atss = new AdminTodaySelectService();
		list = atss.selectDataJson(atss.selectData());
		System.out.println("json--------------------");
		JSONArray array = new JSONArray();
		for (int i = 0; i < list.size(); i++) {
			JSONObject obj = new JSONObject();
			obj.put("visitTodayCount", String.valueOf(list.get(i).getVisitTodayCount()));
			obj.put("visitTotalCount", String.valueOf(list.get(i).getVisitTotalCount()));
			obj.put("postTodayCount", String.valueOf(list.get(i).getPostTodayCount()));
			obj.put("postTotalCount", String.valueOf(list.get(i).getPostTotalCount()));
			obj.put("puppyTodayCount", String.valueOf(list.get(i).getPuppyTodayCount()));
			obj.put("puppyTotalCount", String.valueOf(list.get(i).getPuppyTotalCount()));
			obj.put("regToayCount", String.valueOf(list.get(i).getRegTodayCount()));
			obj.put("regTotalCount", String.valueOf(list.get(i).getRegTotalCount()));
			array.add(obj);
		}
		
		System.out.println(array);
		
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.print(array);
		return null;
	}
}
