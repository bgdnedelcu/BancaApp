package view;

public class BancaView {

	private int id;
	private String numeBanca;
	private String adresa;
	private String swift;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumeBanca() {
		return numeBanca;
	}

	public void setNumeBanca(String numeBanca) {
		this.numeBanca = numeBanca;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getSwift() {
		return swift;
	}

	public void setSwift(String swift) {
		this.swift = swift;
	}

	@Override
	public String toString() {
		return "BancaView [id=" + id + ", numeBanca=" + numeBanca + ", adresa=" + adresa + ", swift=" + swift + "]";
	}

}
