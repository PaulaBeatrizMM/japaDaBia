package dto;


import model.sushi;

public record sushiDto (
		
		Long id,
	    String cliente,
		String prato,
		String descricao,
		Integer valor
		)  {
	public sushi toEntity() {
		return new sushi (this.id, this.cliente, this.prato,this.descricao ,  this.valor);
				
	}

}
