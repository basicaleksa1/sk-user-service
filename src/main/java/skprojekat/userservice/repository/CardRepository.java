package skprojekat.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import skprojekat.userservice.domain.CreditCard;

public interface CardRepository extends JpaRepository<CreditCard, Long>{

		void addCard(CreditCard card);
}
