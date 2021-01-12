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
	
	UserDto findById(Integer id);
	
	UserDto add(UserCreateDto userCreateDto);
	
	void deleteById(Integer id);
	
    TokenResponseDto login(TokenRequestDto tokenRequestDto);

	UserDto addCard(String authorization, CardCreateDto cardCreateDto);

	UserDto updateMiles(String authorization, int miles);

}
