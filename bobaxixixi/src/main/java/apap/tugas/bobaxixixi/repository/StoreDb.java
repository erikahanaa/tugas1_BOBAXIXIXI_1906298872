package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreDb extends JpaRepository<StoreModel, Long>{
    Optional<StoreModel> findByIdStore(long idStore);
    StoreModel findByStoreCode(String storeCode);
    // List<StoreModel> findAllByStoreModels();
}
