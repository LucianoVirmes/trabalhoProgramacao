package principal.model;

import java.time.LocalDate;

public class Funcionario extends Pessoa {

	private Integer codigo;
	private String senha;
	private Double salario;
	private LocalDate dataAdmissao;
	private LocalDate dataDemissao;
	private Filial filial;
	
	public Funcionario() {

	}

	public Funcionario(Integer codigo, String senha, Double salario) {
		super();
		this.codigo = codigo;
		this.senha = senha;
		this.salario = salario;
	}

	public Integer getCodigo() {
		return codigo;
	}
	public void setDataAdmissao(LocalDate dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}
	public LocalDate getDataAdmissao() {
		return dataAdmissao;
	}
	public void setDataDemissao(LocalDate dataDemissao) {
		this.dataDemissao = dataDemissao;
	}
	public LocalDate getDataDemissao() {
		return dataDemissao;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return this.codigo + " : " + this.getNome();
	}

	

}