package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreBobaTeaDb extends JpaRepository<StoreBobaTeaModel, Long>{
}
