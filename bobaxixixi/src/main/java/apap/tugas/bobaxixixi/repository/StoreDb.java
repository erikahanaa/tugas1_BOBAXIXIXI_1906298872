package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

public interface StoreDb extends JpaRepository<StoreModel, Long>{
    Optional<StoreModel> findByIdStore(String idStore);
    // List<StoreModel> findAllByStoreModels();
}
