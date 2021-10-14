package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.ManagerModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ManagerDb extends JpaRepository<ManagerModel, Long>{
    Optional<ManagerModel> findByIdManager(String idManager);
}
