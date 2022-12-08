package view;

public class CarduriView {
	
	private int id;
	private int nrCard;
	private int pin;
	private String nrCont;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNrCard() {
		return nrCard;
	}
	public void setNrCard(int nrCard) {
		this.nrCard = nrCard;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	public String getNrCont() {
		return nrCont;
	}
	public void setNrCont(String nrCont) {
		this.nrCont = nrCont;
	}
	@Override
	public String toString() {
		return "CarduriView [id=" + id + ", nrCard=" + nrCard + ", pin=" + pin + ", nrCont=" + nrCont + "]";
	}
	
	
	

}
