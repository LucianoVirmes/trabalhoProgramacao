package principal.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Devolucao {

	private Integer codigo;
	private LocalDate dataChegada;
	private Double quilometroChegada;
	private Aluguel aluguel;
	private Double valorTotal;
	private TipoPagamento tipoPagamento;
	
	
	/**
	 * 
	 * @return quilometros percorridos durante o aluguel do veiculo
	 */
	public Double calculaQuilometros() {
		if(validaKm()) {
			Double km = this.quilometroChegada - this.aluguel.getQuilometrosSaida();			
			return km;
		}
		return null;
	}
	
	public boolean validaKm() {
		if(this.quilometroChegada > this.aluguel.getQuilometrosSaida()) {
			return true;
		}
		return false;
	}

	/**
	 * (dias * taxa) + (valor * km/1000) 
	 * atualiza valorTotal
	 */
	public void calculaValor() {
		long dias = ChronoUnit.DAYS.between(this.aluguel.getDataAluguel(), this.dataChegada);
		this.valorTotal =(dias * this.aluguel.getTipoAluguel().getTaxa()) + (this.aluguel.getTipoAluguel().getValor() * calculaQuilometros()/1000);
	}
	
	public void disponibilizaCarro() {
		this.aluguel.getCarro().setDisponivel(true);
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataChegada() {
		return dataChegada;
	}

	public void setDataChegada(LocalDate dataChegada) {
		this.dataChegada = dataChegada;
	}

	public Double getQuilometroChegada() {
		return quilometroChegada;
	}

	public void setQuilometroChegada(Double quilometroChegada) {
		this.quilometroChegada = quilometroChegada;
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}

	public Double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public TipoPagamento getTipoPagamento() {
		return tipoPagamento;
	}

	public void setTipoPagamento(TipoPagamento tipoPagamento) {
		this.tipoPagamento = tipoPagamento;
	}

	public Devolucao(Integer codigo, LocalDate dataChegada, Double quilometroChegada, Aluguel aluguel,
			Double valorTotal, TipoPagamento tipoPagamento) {
		super();
		this.codigo = codigo;
		this.dataChegada = dataChegada;
		this.quilometroChegada = quilometroChegada;
		this.aluguel = aluguel;
		this.valorTotal = valorTotal;
		this.tipoPagamento = tipoPagamento;
	}

	public Devolucao() {

	}

	@Override
	public String toString() {
		return "Devolucao [codigo=" + codigo + ", dataChegada=" + dataChegada + ", quilometroChegada="
				+ quilometroChegada + ", aluguel=" + aluguel + ", valorTotal=" + valorTotal + ", tipoPagamento="
				+ tipoPagamento + "]";
	}

}
