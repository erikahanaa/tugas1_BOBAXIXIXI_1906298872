package apap.tugas.bobaxixixi.repository;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BobaTeaDb extends JpaRepository<BobaTeaModel, Long>{
    BobaTeaModel findByIdBoba(String idBoba);
}