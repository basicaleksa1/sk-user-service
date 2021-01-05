package skprojekat.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skprojekat.userservice.domain.Rank;
import skprojekat.userservice.domain.Role;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long>{

    Optional<Rank> findByType(String name);

}
