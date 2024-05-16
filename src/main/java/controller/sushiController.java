package controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import dto.sushiDto;
import service.sushiService;

@RestController
@RequestMapping("/pedido")
public class sushiController {
	
	
	@Autowired
	private sushiService servico;
	
	
	@GetMapping
	public ResponseEntity<List<sushiDto>> listar(){
        return ResponseEntity.ok(servico.listarPedidos());
    
	}
	
	@GetMapping("/{id}")
    public ResponseEntity<sushiDto> buscar(@PathVariable Long id) {
        Optional<sushiDto> sushi = servico.listarPedidoPorId(id);
        if (sushi.isPresent()) {
            return ResponseEntity.ok(sushi.get());
        }
        return ResponseEntity.notFound().build();
    }
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public sushiDto inserir(@Validated @RequestBody sushiDto sushi) {
        return servico.salvarPedido(sushi);
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<sushiDto> atualizar(@PathVariable Long id, @RequestBody sushiDto pratoAlterado) {
        Optional<sushiDto> sushi = servico.atualizarPrato(id, pratoAlterado);

        if (sushi.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sushi.get());
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        if(!servico.excluirPedido(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
	
	@GetMapping("/cliente")
	public ResponseEntity<List<sushiDto>> buscarCliente(@RequestBody String cliente ) {
		
	    return ResponseEntity.ok(servico.obterPorCliente(cliente));
	}
	
	@GetMapping("/pratoMenos")
    public ResponseEntity<List<sushiDto>> buscarPratoMenos(@RequestBody String prato){
        return ResponseEntity.ok(servico.obterPratoMenos(prato));
    }
	
	@GetMapping("/valor")
    public ResponseEntity<List<sushiDto>> obterPratosEntre(@RequestBody entreValoresDto valorDTO){
        int valorMin = valorDTO.valorMin;
        int valorMax = valorDTO.valorMax;
        System.out.println("Chegou aqui");
        
        return ResponseEntity.ok(servico.obterPratosEntre(valorMin, valorMax));
    }
	
	@GetMapping("/buscarPorPrato")
    public ResponseEntity<List<sushiDto>> buscarPorPrato(@RequestParam String prato) {
        List<sushiDto> sushi = servico.findByPratoContainingIgnoreCase(prato);
        if (sushi.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(sushi);
    }


}
