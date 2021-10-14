package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.ToppingModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToppingDb extends JpaRepository<ToppingModel, Long>{
    Optional<ToppingModel> findByIdTopping(String idTopping);
}
