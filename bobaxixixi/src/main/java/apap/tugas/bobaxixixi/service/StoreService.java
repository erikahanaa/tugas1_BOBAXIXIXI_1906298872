package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreModel;
import java.util.List;

public interface StoreService {
    void addStore(StoreModel store);
    List<StoreModel> getListStore();
    String getStoreCode(StoreModel store);
    StoreModel getStoreByStoreCode(String storeCode);
    StoreModel getStoreByIdStore(Long idStore);
}
