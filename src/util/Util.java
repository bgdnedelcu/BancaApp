package util;

import java.util.List;
import java.util.Optional;

import db.BazaUtil;
import view.CarduriView;
import view.ConturiView;

public class Util {

	public CarduriView verificarCard(int nrCard) {
		CarduriView carduri = null;
		Optional<CarduriView> optional = null;
		List<CarduriView> listaCarduri = new BazaUtil().getCarduri();

		for (CarduriView card : listaCarduri) {
			if (card.getNrCard() == nrCard) {
				carduri = card;
			}
		}	
		return carduri;
	}

	public boolean verificaCont(String nrCont) {
		
		
		return false;
		
	}
	

}
