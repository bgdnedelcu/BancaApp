package view;

public class ConturiView {
	
	private int id;
	private int idBanca;
	private int idPersoana;
	private int sold;
	private String nrCont;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdBanca() {
		return idBanca;
	}
	public void setIdBanca(int idBanca) {
		this.idBanca = idBanca;
	}
	public int getIdPersoana() {
		return idPersoana;
	}
	public void setIdPersoana(int idPersoana) {
		this.idPersoana = idPersoana;
	}
	public int getSold() {
		return sold;
	}
	public void setSold(int sold) {
		this.sold = sold;
	}
	public String getNrCont() {
		return nrCont;
	}
	public void setNrCont(String nrCont) {
		this.nrCont = nrCont;
	}
	@Override
	public String toString() {
		return "ConturiView [id=" + id + ", idBanca=" + idBanca + ", idPersoana=" + idPersoana + ", sold=" + sold
				+ ", nrCont=" + nrCont + "]";
	}

	
}
