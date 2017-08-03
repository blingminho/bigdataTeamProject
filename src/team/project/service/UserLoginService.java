package team.project.service;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import team.project.dao.UserDao;
import team.project.dto.User;

public class UserLoginService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		System.out.println("2. UserLoginService");
		// 1. ��û�Ķ���� ó��
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		User user = new User();
		user.setEmail(email);
		user.setPassword(password);

		// 2. DBó��
		String result = "";
		try {
			result = UserDao.getInstance().login(user);
			System.out.println(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 3. DB��� ó��

		// 4. ���������� ó��

		NextPage nextPage = new NextPage();
		HttpSession session = request.getSession();

		// ȸ������ ����
		if (result != null) {
			nextPage.setPageName("./jsp/index.jsp");
			session.setAttribute("name", result);
			nextPage.setRedirect(true);
		}
		// ȸ������ ����
		else {
			request.setAttribute("errorMSG", "�α��ο� �����߽��ϴ�.");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
