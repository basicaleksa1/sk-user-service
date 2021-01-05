package skprojekat.userservice.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import skprojekat.userservice.domain.Rank;
import skprojekat.userservice.domain.Role;
import skprojekat.userservice.domain.User;
import skprojekat.userservice.repository.RankRepository;
import skprojekat.userservice.repository.RoleRepository;
import skprojekat.userservice.repository.UserRepository;


@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private RankRepository rankRepository;

    public TestDataRunner(RoleRepository roleRepository,
    		UserRepository userRepository, RankRepository rankRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.rankRepository = rankRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        //Insert roles
        Role roleUser = new Role("ROLE_USER");
        Role roleAdmin = new Role("ROLE_ADMIN");
        roleRepository.save(roleUser);
        roleRepository.save(roleAdmin);
        //Insert ranks
        Rank rankBronze = new Rank("Bronze", 0, 0);
        Rank rankSilver = new Rank("Silver", 1000, 10);
        Rank rankGold = new Rank("Gold", 10000, 20);
        rankRepository.save(rankBronze);
        rankRepository.save(rankSilver);
        rankRepository.save(rankGold);
        //Insert admin
        User admin = new User();
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");
        admin.setRole(roleAdmin);
        admin.setFirst_name("admin");
        admin.setPassport(12312313);
        admin.setLast_name("adminic");

        userRepository.save(admin);
    }
}

