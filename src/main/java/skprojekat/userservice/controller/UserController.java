package skprojekat.userservice.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import skprojekat.userservice.service.UserService;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private UserService serviceUser;
	
	public UserController(UserService serviceUser) {
		this.serviceUser = serviceUser;
	}
	
	/*public ResponseEntity<Page<FlightDto>> findAll(@ApiIgnore Pageable pageable){
		return new ResponseEntity<>(service.findAll(pageable), HttpStatus.OK);
	}*/
}
