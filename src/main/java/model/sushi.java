package model;


import dto.sushiDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cardapio")
public class sushi {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String cliente;
	private String prato;
	private String descricao;
	private Integer valor;
	
	
	public sushi() {
		
	}
	
	
	public sushi(Long id, String cliente, String prato, String descricao, Integer valor) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.prato = prato;
		this.descricao = descricao;
		this.valor = valor;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getPrato() {
		return prato;
	}
	public void setPrato(String prato) {
		this.prato = prato;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getValor() {
		return valor;
	}
	public void setValor(Integer valor) {
		this.valor = valor;
	}


	public sushiDto toDto() {
		return new sushiDto(this.id, this.cliente, this.prato, this.descricao,
                this.valor);
	}
	

}
