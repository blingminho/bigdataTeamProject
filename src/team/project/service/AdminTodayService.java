package team.project.service;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import team.project.dto.Admin;


public class AdminTodayService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2.AdminTodayService");
		Admin admin = new AdminTodaySelectService().selectData();
		
		// 4. 페이지 이동
		NextPage nextPage = new NextPage();

		if (admin != null) {
			System.out.println("널값 아니다");
			//nextPage.setPageName("./jsp/admin.jsp");
			nextPage.setPageName("./jsp/adminindex.jsp");
			request.getSession().setAttribute("adminToday", admin);
			nextPage.setRedirect(true);
		}
		else {
			System.out.println("널값이다 알려줘");
			request.setAttribute("errorMSG", "오류발생");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
