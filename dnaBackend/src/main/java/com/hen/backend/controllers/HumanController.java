package com.hen.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hen.backend.exception.ResourceNotFoundException; 
import com.hen.backend.models.Human; 
import com.hen.backend.repository.HumanRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
/**
 *
 * @author Henrry Gómez
 */
@RestController
@RequestMapping("/api/v1")
public class HumanController {

    public HumanController() {
    }
    
        
    
	@Autowired
	private HumanRepository humanRepository;

	@CrossOrigin(origins="*")
        @GetMapping("/human")
	public List<Human> getAllHumans() {
		return humanRepository.findAll();
	}

	@GetMapping("/human/{id}")
	public ResponseEntity<Human> getHumanById(@PathVariable(value = "id") Long humanId)
			throws ResourceNotFoundException {
		Human human = humanRepository.findById(humanId)
				.orElseThrow(() -> new ResourceNotFoundException("Human not found for this id :: " + humanId));
		return ResponseEntity.ok().body(human);
	}

	@CrossOrigin(origins="*")
        @PostMapping("/human")
	public Human createHuman(@Valid @RequestBody Human human) {
		return humanRepository.save(human);
	}

	@PutMapping("/human/{id}")
	public ResponseEntity<Human> updateupdatedHuman(@PathVariable(value = "id") Long humanId,
			@Valid @RequestBody Human humanDetails) throws ResourceNotFoundException {
		Human human = humanRepository.findById(humanId)
				.orElseThrow(() -> new ResourceNotFoundException("Human not found for this id :: " + humanId));

		human.setName(humanDetails.getName());
                human.setDna(humanDetails.getDna());
                human.setHasMutation(humanDetails.isHasMutation());
                        
		final Human updatedHuman = humanRepository.save(human);
		return ResponseEntity.ok(updatedHuman);
	}

	@DeleteMapping("/human/{id}")
	public Map<String, Boolean> deleteHuman(@PathVariable(value = "id") Long humanId)
			throws ResourceNotFoundException {
		Human human = humanRepository.findById(humanId)
				.orElseThrow(() -> new ResourceNotFoundException("Human not found for this id :: " + humanId));

		humanRepository.delete(human);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
