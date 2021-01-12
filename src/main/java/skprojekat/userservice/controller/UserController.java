package skprojekat.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import skprojekat.userservice.domain.User;
import skprojekat.userservice.dto.CardCreateDto;
import skprojekat.userservice.dto.TokenRequestDto;
import skprojekat.userservice.dto.TokenResponseDto;
import skprojekat.userservice.dto.UserCreateDto;
import skprojekat.userservice.dto.UserDto;
import skprojekat.userservice.service.UserService;

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

	 @ApiOperation(value = "Register user")
	 @PostMapping
	 public ResponseEntity<UserDto> saveUser(@RequestBody UserCreateDto userCreateDto) {
		 return new ResponseEntity<>(serviceUser.add(userCreateDto), HttpStatus.CREATED);
	 }

	 @ApiOperation(value = "Add card")
	 @PutMapping("/addCard")
	 public ResponseEntity<UserDto> addCard(@RequestHeader("Authorization") String authorization, @RequestBody CardCreateDto cardCreateDto){
		 return new ResponseEntity<>(serviceUser.addCard(authorization, cardCreateDto), HttpStatus.CREATED);
	 }

	 @ApiOperation(value = "Login")
	 @PostMapping("/login")
	 public ResponseEntity<TokenResponseDto> loginUser(@RequestBody TokenRequestDto tokenRequestDto) {
		 return new ResponseEntity<>(serviceUser.login(tokenRequestDto), HttpStatus.OK);
	 }

	 @GetMapping("/{id}")
	 public ResponseEntity<UserDto> findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id){
		 return new ResponseEntity<UserDto>(serviceUser.findById(id), HttpStatus.OK);
	 }

	 @PutMapping("/updateMiles")
	 public ResponseEntity<UserDto> updateMiles(@RequestHeader("Authorization") String authorization, @RequestBody Integer miles){
		 return new ResponseEntity<>(serviceUser.updateMiles(authorization, miles), HttpStatus.OK);
	 }
}
