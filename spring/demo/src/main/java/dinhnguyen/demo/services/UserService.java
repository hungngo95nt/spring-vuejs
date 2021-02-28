package dinhnguyen.demo.services;

import javax.servlet.http.HttpServletRequest;

import dinhnguyen.demo.entity.User;

public interface UserService {
	
	public String signin(String username, String password);

	public String signup(User user);

	public void delete(String username);

	public User search(String username);

	public User whoami(HttpServletRequest req);

	public String refresh(String username);

}
