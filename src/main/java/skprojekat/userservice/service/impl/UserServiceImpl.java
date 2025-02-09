package skprojekat.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import skprojekat.userservice.domain.CreditCard;
import skprojekat.userservice.domain.Rank;
import skprojekat.userservice.domain.Role;
import skprojekat.userservice.domain.User;
import skprojekat.userservice.dto.CardCreateDto;
import skprojekat.userservice.dto.TokenRequestDto;
import skprojekat.userservice.dto.TokenResponseDto;
import skprojekat.userservice.dto.UserCreateDto;
import skprojekat.userservice.dto.UserDto;
import skprojekat.userservice.map.CardMapper;
import skprojekat.userservice.map.UserMapper;
import skprojekat.userservice.repository.CardRepository;
import skprojekat.userservice.repository.RankRepository;
import skprojekat.userservice.repository.RoleRepository;
import skprojekat.userservice.repository.UserRepository;
import skprojekat.userservice.security.service.TokenService;
import skprojekat.userservice.service.EmailService;
import skprojekat.userservice.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private UserRepository userRepo;
	private RankRepository rankRepo;
	private RoleRepository roleRepo;
	private CardRepository cardRepo;
	private CardMapper cardMapper;
	private UserMapper userMapper;
	private TokenService tokenService;
	private EmailService emailService;
	
	public UserServiceImpl(UserRepository userRepo, UserMapper userMapper, RankRepository rankRepo, 
			               RoleRepository roleRepo, CardRepository cardRepo, CardMapper cardMapper,
			               TokenService tokenService, EmailService emailService) {
		this.userRepo = userRepo;
		this.userMapper = userMapper;
		this.rankRepo = rankRepo;
		this.roleRepo = roleRepo;
		this.cardRepo = cardRepo;
		this.cardMapper = cardMapper;
		this.tokenService = tokenService;
		this.emailService = emailService;
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
	public UserDto findById(Integer id) {
		User user = userRepo.findById(id).orElseThrow();
		return userMapper.userToUserDto(user);
	}
	
	@Override
	public UserDto add(UserCreateDto userCreateDto) {
		User user = userMapper.userCreateDtoToUser(userCreateDto);
		Rank rank = rankRepo.findByType("Bronze").orElseThrow();
		Role role = roleRepo.findByName("ROLE_USER").orElseThrow();
		user.setRank(rank);
		user.setRole(role);
		userRepo.save(user);
		System.out.println(user.getEmail());
		emailService.sendSimpleMessage(user.getEmail(), "Registracija", "Uspesno ste registrovani!");
		
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
        System.out.println(claims + "EEEEEEEEEEEEEEEEEEEEEEEEEEEEee");
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());
        //Generate token
        return new TokenResponseDto(tokenService.generate(claims));
	}

	@Override
	public UserDto addCard(String authorization, CardCreateDto cardCreateDto) {
		Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        int id = claims.get("id", Integer.class);
        
        User curr_user = userRepo.findById(id).orElseThrow();
        CreditCard card = cardMapper.cardCreateDtoToCard(cardCreateDto);
        card.setUser(curr_user);
        cardRepo.save(card);
        curr_user.addCard(card);

		return userMapper.userToUserDto(curr_user);
	}

	@Override
	public UserDto updateMiles(String authorization, int miles) {
		Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
		int id = claims.get("id", Integer.class);

		User curr_user = userRepo.findById(id).orElseThrow();
		System.out.println(curr_user.getEmail());
		curr_user.setMiles(curr_user.getMiles() + miles);
		Rank bronze = rankRepo.findByType("Bronze").orElseThrow();
		Rank silver = rankRepo.findByType("Silver").orElseThrow();
		Rank gold = rankRepo.findByType("Gold").orElseThrow();
		if(curr_user.getMiles() > 1000){
			curr_user.setRank(silver);
		}
		else if(curr_user.getMiles() > 10000){
			curr_user.setRank(gold);
		}
		userRepo.save(curr_user);
		return userMapper.userToUserDto(curr_user);
	}

	@Override
	public UserDto editUser(String authorization, UserCreateDto userCreateDto) {
		Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
		int id = claims.get("id", Integer.class);

		User curr_user = userRepo.findById(id).orElseThrow();
		curr_user.setFirst_name(userCreateDto.getFirst_name());
		curr_user.setLast_name(userCreateDto.getLast_name());
		curr_user.setEmail(userCreateDto.getEmail());
		curr_user.setPassport(userCreateDto.getPassport());
		curr_user.setPassword(userCreateDto.getPassword());
		return userMapper.userToUserDto(curr_user);
	}


}
