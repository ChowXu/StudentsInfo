package action;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.hibernate.Session;

import service.UsersDAO;
import serviceImpl.UsersDaoImpl;

import com.opensymphony.xwork2.ModelDriven;

import entity.Users;

public class UsersAction extends SuperAction implements ModelDriven<Users> {

	private Users user = new Users();

	// users login
	public String login() {
		UsersDAO uDao = new UsersDaoImpl();

		if (uDao.userLogin(user)) {
			// save user in session
			session.setAttribute("loginUserName", user.getUsername());
			return "login_success";
		} else {
			return "login_failure";
		}
	}

	@SkipValidation
	public String logout() {
		if (session.getAttribute("loginUserName") != null) {
			session.removeAttribute("loginUserName");
		}
		return "logout";
	}

	@Override
	public void validate() {
		// username is not null
		if ("".equals(user.getUsername().trim())) {
			this.addFieldError("usernameError", "Username can not be null");

		}
		if(user.getPassword().length() > 3){
			this.addFieldError("passwordError", "password too shart");
		}

	}

	public Users getModel() {

		return this.user;
	}

}
