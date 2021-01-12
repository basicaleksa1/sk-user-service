package skprojekat.userservice.map;

import org.springframework.stereotype.Component;


import skprojekat.userservice.domain.User;
import skprojekat.userservice.dto.UserCreateDto;
import skprojekat.userservice.dto.UserDto;
import skprojekat.userservice.repository.RankRepository;
import skprojekat.userservice.repository.RoleRepository;


@Component
public class UserMapper {
	
    private RoleRepository roleRepository;
    private RankRepository rankRepository;

    public UserMapper(RoleRepository rr, RankRepository rpr) {
    	this.roleRepository = rr;
    	this.rankRepository = rpr;
    }
	
	public UserDto userToUserDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirst_name(user.getFirst_name());
		userDto.setLast_name(user.getLast_name());
		userDto.setEmail(user.getEmail());
		userDto.setPassport(user.getPassport());
		userDto.setPassword(user.getPassword());
		userDto.setMiles(user.getMiles());
		userDto.setRank(user.getRank());
		
		return userDto;
	}
	
	public User userCreateDtoToUser(UserCreateDto userCD) {
		User user = new User();
		user.setFirst_name(userCD.getFirst_name());
		user.setLast_name(userCD.getLast_name());
		user.setEmail(userCD.getEmail());
		user.setPassword(userCD.getPassword());
		user.setPassport(userCD.getPassport());
        user.setMiles(userCD.getMiles());		
        return user;
	}
}
