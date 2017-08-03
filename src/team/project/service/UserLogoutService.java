package team.project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import team.project.dao.UserDao;

public class UserLogoutService implements UserService {

	@Override
	public NextPage execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("2.UserLogoutService");
		// 1. ��û�Ķ���� ó��
		HttpSession session = request.getSession();

		// 2. DBó��
		System.out.println(session.getAttribute("name"));
		session.removeAttribute("name");
		System.out.println(session.getAttribute("name"));
		// 3. DB��� ó��
		String result = "";
		result = (String) session.getAttribute("name");
		// 4. ���������� ó��
		System.out.println(result);
		NextPage nextPage = new NextPage();
		// �α׾ƿ�
		if (result == null) {
			nextPage.setPageName("./jsp/index.jsp");
			nextPage.setRedirect(true);
		}
		// �α׾ƿ� ����
		else {
			request.setAttribute("errorMSG", "�α��ο� �����߽��ϴ�.");
			nextPage.setPageName("error.jsp");
			nextPage.setRedirect(false);
		}
		return nextPage;
	}

}
