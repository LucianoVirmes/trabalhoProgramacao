package principal.model;

import java.time.LocalDate;

public class View {
	
	private String string1;
	private String string2;
	private String string3;
	private LocalDate dataInicio;
	private String nomeFilial;
	private String uf;
	private LocalDate ultimaModificacao;
	
	public View(String string1, String string2, String string3, LocalDate dataInicio, String nomeFilial, String uf,
			LocalDate ultimaModificacao) {
		super();
		this.string1 = string1;
		this.string2 = string2;
		this.string3 = string3;
		this.dataInicio = dataInicio;
		this.nomeFilial = nomeFilial;
		this.uf = uf;
		this.ultimaModificacao = ultimaModificacao;
	}

	public View() {

	}

	public String getString1() {
		return string1;
	}

	public void setString1(String string1) {
		this.string1 = string1;
	}

	public String getString2() {
		return string2;
	}

	public void setString2(String string2) {
		this.string2 = string2;
	}

	public String getString3() {
		return string3;
	}

	public void setString3(String string3) {
		this.string3 = string3;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public String getNomeFilial() {
		return nomeFilial;
	}

	public void setNomeFilial(String nomeFilial) {
		this.nomeFilial = nomeFilial;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public LocalDate getUltimaModificacao() {
		return ultimaModificacao;
	}

	public void setUltimaModificacao(LocalDate ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
	}
	
	
	
	

}
