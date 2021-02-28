package dinhnguyen.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dinhnguyen.demo.entity.Role;
import dinhnguyen.demo.entity.User;
import dinhnguyen.demo.form.UserForm;
import dinhnguyen.demo.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;

@RestController
@RequestMapping("/users")
@Api(tags = "users")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/signin")
	@ApiOperation(value = "${UserController.signin}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 422, message = "Invalid username/password supplied") })
	public String login(//
			@ApiParam("Username") @RequestParam String username, //
			@ApiParam("Password") @RequestParam String password) {
		return userService.signin(username, password);
	}

	@PostMapping("/signup")
	@ApiOperation(value = "${UserController.signup}")
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 422, message = "Username is already in use") })
	public String signup(@ApiParam("Signup User") @RequestBody User user) {
		return userService.signup(user);
	}

	@PostMapping("/api/signup")
	public ResponseEntity<String> signup(@RequestBody UserForm form) {
		User user = new User();
		user.setUsername(form.getName());
		user.setPassword(form.getPassword());
		user.setEmail(form.getEmail());
		if (form.getRole().equals("Reader")) {
			user.setRoles(new ArrayList<Role>(Arrays.asList(Role.READER)));
		}
		if (form.getRole().equals("Writer")) {
			user.setRoles(new ArrayList<Role>(Arrays.asList(Role.WRITER)));
		}
		if (form.getRole().equals("Admin")) {
			user.setRoles(new ArrayList<Role>(Arrays.asList(Role.ADMIN)));
		}
		userService.signup(user);
		return new ResponseEntity<String>("Register success", HttpStatus.OK);

	}

	@DeleteMapping(value = "/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "${UserController.delete}", authorizations = { @Authorization(value = "apiKey") })
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public String delete(@ApiParam("Username") @PathVariable String username) {
		userService.delete(username);
		return username;
	}

	@GetMapping(value = "/{username}")
	@PreAuthorize("hasRole('ADMIN')")
	@ApiOperation(value = "${UserController.search}", response = User.class, authorizations = {
			@Authorization(value = "apiKey") })
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 404, message = "The user doesn't exist"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public User search(@ApiParam("Username") @PathVariable String username) {
		return userService.search(username);
	}

	@GetMapping(value = "/me")
	@PreAuthorize("hasRole('ADMIN') or hasRole('WRITER')")
	@ApiOperation(value = "${UserController.me}", response = User.class, authorizations = {
			@Authorization(value = "apiKey") })
	@ApiResponses(value = { //
			@ApiResponse(code = 400, message = "Something went wrong"), //
			@ApiResponse(code = 403, message = "Access denied"), //
			@ApiResponse(code = 500, message = "Expired or invalid JWT token") })
	public User whoami(HttpServletRequest req) {
		return userService.whoami(req);
	}

	@GetMapping("/refresh")
	@PreAuthorize("hasRole('ADMIN') or hasRole('WRITER')")
	public String refresh(HttpServletRequest req) {
		return userService.refresh(req.getRemoteUser());
	}

}
