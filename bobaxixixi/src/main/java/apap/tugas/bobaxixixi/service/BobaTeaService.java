package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import java.util.List;

public interface BobaTeaService {
    void addBobaTea(BobaTeaModel bobaTea);
    List<BobaTeaModel> getListBobaTea();
    BobaTeaModel getBobaTeaById(Long idBoba);
    BobaTeaModel getBobaTeaByNama(String namaBoba);
    BobaTeaModel updateBobaTea(BobaTeaModel bobaTea);
    void deleteBobaTea(BobaTeaModel bobaTea);
}

