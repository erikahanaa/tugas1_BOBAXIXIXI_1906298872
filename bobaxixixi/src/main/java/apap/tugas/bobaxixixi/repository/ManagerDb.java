package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.ManagerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

public interface ManagerDb extends JpaRepository<ManagerModel, Long>{
    // ManagerModel findByName (String namaManager);
    Optional<ManagerModel> findByIdManager(String idManager);
}
