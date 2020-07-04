package com.hen.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hen.backend.models.Human;
/**
 *
 * @author Henrry Gómez
 */
@Repository
public interface HumanRepository extends JpaRepository<Human, Long>{

}
