package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import java.util.List;

public interface StoreBobaTeaService {
    void addStoreBobaTea(StoreBobaTeaModel storeBobaTea);
    List<StoreBobaTeaModel> getListStoreBobaTea();
    String getProductionCode(StoreBobaTeaModel storeBobaTea);
    void deleteStoreBobaTea(StoreBobaTeaModel storeBobaTea);
}
