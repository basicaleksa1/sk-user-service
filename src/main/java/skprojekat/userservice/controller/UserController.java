package skprojekat.userservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import skprojekat.userservice.domain.User;
import skprojekat.userservice.dto.UserDto;
import skprojekat.userservice.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService serviceUser;
	
	public UserController(UserService serviceUser) {
		this.serviceUser = serviceUser;
	}

	@GetMapping
	public ResponseEntity<List<UserDto>> findAll(){
		return new ResponseEntity<List<UserDto>>(serviceUser.findAll(), HttpStatus.OK);
	}
}
