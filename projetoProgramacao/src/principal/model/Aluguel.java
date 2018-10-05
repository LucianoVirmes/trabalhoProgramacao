package principal.model;

import java.time.LocalDate;

public class Aluguel {

	private Integer codigo;
	private LocalDate dataAluguel;
	private Double kmSaida;
	private Carro carro;
	private Filial filial;
	private Funcionario funcionario;
	private TipoAluguel tipoAluguel;
	private Cliente cliente;
	
	public boolean confirmaAluguel() {
		if(this.carro.isDisponivel()) {
			this.carro.setDisponivel(false);
			return true;
		}else {
			return false;
		}
	}


	public Aluguel() {
		
	}

	public Aluguel(Integer codigo, LocalDate dataAluguel, Double kmSaida, Carro carro, Filial filial,
			Funcionario funcionario, TipoAluguel tipoAluguel, Cliente cliente) {
		super();
		this.codigo = codigo;
		this.dataAluguel = dataAluguel;
		this.kmSaida = kmSaida;
		this.carro = carro;
		this.filial = filial;
		this.funcionario = funcionario;
		this.tipoAluguel = tipoAluguel;
		this.cliente = cliente;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public LocalDate getDataAluguel() {
		return dataAluguel;
	}

	public void setDataAluguel(LocalDate dataAluguel) {
		this.dataAluguel = dataAluguel;
	}

	public Double getQuilometrosSaida() {
		return kmSaida;
	}

	public void setQuilometrosSaida(Double quilometrosSaida) {
		this.kmSaida = quilometrosSaida;
	}

	public Carro getCarro() {
		return carro;
	}

	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	public Filial getFilial() {
		return filial;
	}

	public void setFilial(Filial filial) {
		this.filial = filial;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public TipoAluguel getTipoAluguel() {
		return tipoAluguel;
	}

	public void setTipoAluguel(TipoAluguel tipoAluguel) {
		this.tipoAluguel = tipoAluguel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Aluguel [codigo=" + codigo + ", dataAluguel=" + dataAluguel + ", quilometrosSaida=" + kmSaida
				+ ", carro=" + carro + ", filial=" + filial + ", funcionario=" + funcionario + ", tipoAluguel="
				+ tipoAluguel + ", cliente=" + cliente + "]";
	}

}