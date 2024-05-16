package repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import dto.sushiDto;
import model.sushi;



public interface sushiRepository extends JpaRepository<sushi, Long> {

	List<sushiDto> findByValorBetween(String valorMinimo, String valorMaximo);

	List<sushiDto> findByPratoContainingIgnoreCase(String prato);

	List<sushiDto> findByPratoNotLike(String prato);

	List<sushiDto> findByClienteLike(String cliente);
	
	
	

}
