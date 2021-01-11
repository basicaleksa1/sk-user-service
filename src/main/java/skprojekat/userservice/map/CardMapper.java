package skprojekat.userservice.map;

import org.springframework.stereotype.Component;

import skprojekat.userservice.domain.CreditCard;
import skprojekat.userservice.dto.CardCreateDto;
import skprojekat.userservice.dto.CardDto;

@Component
public class CardMapper {
	
	public CardMapper() {
		
	}
	
	public CardDto cardToCardDto(CreditCard card) {
		CardDto cardDto = new CardDto();
		cardDto.setFull_name(card.getFull_name());
		cardDto.setCard_num(card.getCard_num());
		return cardDto;
	}
	
	public CreditCard cardCreateDtoToCard(CardCreateDto cardCD) {
		CreditCard card = new CreditCard();
		card.setFull_name(cardCD.getFull_name());
		card.setCard_num(cardCD.getCard_num());
		card.setCard_code(cardCD.getCard_code());
		return card;
	}
}
