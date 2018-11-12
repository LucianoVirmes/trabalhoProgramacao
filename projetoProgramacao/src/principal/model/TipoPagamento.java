package principal.model;

public class TipoPagamento {

	private Integer codigo;
	private String descricao;
	private Double desconto;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public TipoPagamento(Integer codigo, String descricao, Double desconto) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.desconto = desconto;
	}

	public TipoPagamento() {

	}

	@Override
	public String toString() {
		return this.descricao + " : " + this.desconto.toString();
	}

	
}
