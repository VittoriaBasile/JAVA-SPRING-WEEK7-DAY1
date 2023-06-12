package epicode.JAVASPRINGWEEK7DAY1.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import epicode.JAVASPRINGWEEK7DAY1.entities.Citta;

@Repository
public interface CittaRepository extends JpaRepository<Citta, UUID> {

}
