package service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dto.sushiDto;
import model.sushi;
import repository.sushiRepository;



@Service
public class sushiService {
	@Autowired
	private sushiRepository sushirepo;
	
	public List<sushiDto> listarPedidos() {
		return sushirepo.findAll().stream()
				.map(c -> new sushiDto(c.getId(), c.getCliente(), c.getPrato(), c.getDescricao(), c.getValor()))
				.toList();
	
	}
	
	public Optional<sushiDto> listarPedidoPorId(Long id) {
		Optional<sushi> sushi = sushirepo.findById(id);
		if (sushi.isPresent()) {
			return Optional.of(sushi.get().toDto());
		} 
		return Optional.empty();
	}
	
	public sushiDto salvarPedido(sushiDto sushi) {
		sushi sushiEntity = sushirepo.save(sushi.toEntity());
        return sushiEntity.toDto();
    }
	
	public Optional<sushiDto> atualizarPrato(Long id, sushiDto sushi) {
		sushi entSushi = sushi.toEntity();
		if (sushirepo.existsById(id)) {
			entSushi.setId(id);
			sushirepo.save(entSushi);
			return Optional.of(entSushi.toDto());
		} 
		return Optional.empty();
	}
	
	public boolean excluirPedido(Long id) {
        if(!sushirepo.existsById(id)){
            return false;
        }

        sushirepo.deleteById(id);
        return true;
    }
	
	public List<sushiDto> obterPorCliente(String cliente) {
		return sushirepo.findByClienteLike(cliente).stream()
				.map(c -> new sushiDto(c.id(), c.cliente(), c.prato(), c.descricao(), c.valor()))
				.toList();
		
	}
	
	public List<sushiDto> obterPratoMenos(String prato) {
        return sushirepo.findByPratoNotLike(prato).stream()
                .map(c -> new sushiDto(c.id(), c.cliente(), c.prato(), c.descricao(), c.valor()))
				.toList();
    }
	
	public List<sushiDto> obterPratosEntre(int valorMin, int ValorMax) {
        String valorMinimo = String.valueOf(valorMin);
        String valorMaximo = String.valueOf(ValorMax);
        return sushirepo.findByValorBetween(valorMinimo, valorMaximo).stream()
                .map(c -> new sushiDto(c.id(), c.cliente(), c.prato(), c.descricao(), c.valor()))
                .toList();
    }
	
	public List<sushiDto> findByPratoContainingIgnoreCase(String prato) {
        return sushirepo.findByPratoContainingIgnoreCase(prato).stream()
            .map(c -> new sushiDto(c.id(), c.cliente(), c.prato(), c.descricao(), c.valor()))
            .toList();
    }

	
}

	
	


