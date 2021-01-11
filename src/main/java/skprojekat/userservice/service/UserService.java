package skprojekat.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import skprojekat.userservice.domain.User;
import skprojekat.userservice.dto.CardCreateDto;
import skprojekat.userservice.dto.TokenRequestDto;
import skprojekat.userservice.dto.TokenResponseDto;
import skprojekat.userservice.dto.UserCreateDto;
import skprojekat.userservice.dto.UserDto;

@Service
public interface UserService {
	
	List<UserDto> findAll();
	
	Optional<User> findById(Integer id);
	
	UserDto add(UserCreateDto userCreateDto);
	
	UserDto addCard(CardCreateDto cardCreateDto);
	
	void deleteById(Integer id);
	
    TokenResponseDto login(TokenRequestDto tokenRequestDto);

}
