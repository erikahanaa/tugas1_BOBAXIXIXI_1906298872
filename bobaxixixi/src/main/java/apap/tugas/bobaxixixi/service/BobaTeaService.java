package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import java.util.List;
import java.util.Optional;

public interface BobaTeaService {
    void addBobaTea(BobaTeaModel bobaTea);
    List<BobaTeaModel> getListBobaTea();
    Optional<BobaTeaModel> getBobaTeaById(Long idBoba);
}

