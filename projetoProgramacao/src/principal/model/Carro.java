package principal.model;

import java.time.LocalDate;

public class Carro {
	private Integer codigo;
	private String marca;
	private String modelo;
	private Double valor;
	private String cor;
	private LocalDate ano;
	private String placa;
	private boolean disponivel;
	
	private LocalDate dataDeAquisicao;
	private LocalDate dataDeDesapropriacao = null;
	private Filial filial;

	public Carro() {

	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public LocalDate getAno() {
		return ano;
	}

	public void setAno(LocalDate ano) {
		this.ano = ano;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public boolean isDisponivel() {
		return disponivel;
	}

	public void setDisponivel(boolean disponivel) {
		this.disponivel = disponivel;
	}

	public Carro(Integer codigo, String marca, String modelo, Double valor, String cor, LocalDate ano, String placa,
			boolean disponivel) {
		super();
		this.codigo = codigo;
		this.marca = marca;
		this.modelo = modelo;
		this.valor = valor;
		this.cor = cor;
		this.ano = ano;
		this.placa = placa;
		this.disponivel = disponivel;
	}

	@Override
	public String toString() {
		return this.marca + " [" + this.modelo + "] - " + this.placa ;
	}

	public LocalDate getDataDeAquisicao() {
		return dataDeAquisicao;
	}

	public void setDataDeAquisicao(LocalDate dataDeAquisicao) {
		this.dataDeAquisicao = dataDeAquisicao;
	}

	public LocalDate getDataDeDesapropriacao() {
		return dataDeDesapropriacao;
	}

	public void setDataDeDesapropriacao(LocalDate dataDeDesapropriacao) {
		this.dataDeDesapropriacao = dataDeDesapropriacao;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

}
