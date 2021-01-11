package skprojekat.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import skprojekat.userservice.domain.Rank;
import skprojekat.userservice.domain.Role;
import skprojekat.userservice.domain.User;
import skprojekat.userservice.dto.CardCreateDto;
import skprojekat.userservice.dto.TokenRequestDto;
import skprojekat.userservice.dto.TokenResponseDto;
import skprojekat.userservice.dto.UserCreateDto;
import skprojekat.userservice.dto.UserDto;
import skprojekat.userservice.map.UserMapper;
import skprojekat.userservice.repository.CardRepository;
import skprojekat.userservice.repository.RankRepository;
import skprojekat.userservice.repository.RoleRepository;
import skprojekat.userservice.repository.UserRepository;
import skprojekat.userservice.security.service.TokenService;
import skprojekat.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;
	private RankRepository rankRepo;
	private RoleRepository roleRepo;
	private CardRepository cardRepo;
	private UserMapper userMapper;
	private TokenService tokenService;
	
	public UserServiceImpl(UserRepository userRepo,
		   UserMapper userMapper, RankRepository rankRepo, RoleRepository roleRepo, CardRepository cardRepo) {
		this.userRepo = userRepo;
		this.userMapper = userMapper;
		this.rankRepo = rankRepo;
		this.roleRepo = roleRepo;
		this.cardRepo = cardRepo;
	}

	@Override
	public List<UserDto> findAll() {
		List<UserDto> result_list = new ArrayList<UserDto>();
		
		for (User user : userRepo.findAll()){
			result_list.add(userMapper.userToUserDto(user));
		}
		
		return result_list;
	}

	@Override
	public void deleteById(Integer id) {		
		userRepo.deleteById(id);
	}

	@Override
	public Optional<User> findById(Integer id) {
		return userRepo.findById(id);
	}
	
	@Override
	public UserDto add(UserCreateDto userCreateDto) {
		User user = userMapper.userCreateDtoToUser(userCreateDto);
		Rank rank = rankRepo.findByType("Bronze").orElseThrow();
		Role role = roleRepo.findByName("ROLE_USER").orElseThrow();
		user.setRank(rank);
		user.setRole(role);
		userRepo.save(user);
        return userMapper.userToUserDto(user);
	}

	@Override
	public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
		 //Try to find active user for specified credentials
        User user = userRepo
                .findUserByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow();
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
	}

	@Override
	public UserDto addCard(CardCreateDto cardCreateDto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
