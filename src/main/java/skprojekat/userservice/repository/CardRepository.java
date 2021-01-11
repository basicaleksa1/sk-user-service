package skprojekat.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import skprojekat.userservice.domain.CreditCard;
@Repository
public interface CardRepository extends JpaRepository<CreditCard, Long>{

}
