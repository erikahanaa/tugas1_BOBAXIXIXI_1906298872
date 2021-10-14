package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.repository.StoreDb;
import net.bytebuddy.utility.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreDb storeDb;

    @Override
    public void addStore(StoreModel store){
        storeDb.save(store);
    }

    @Override
    public List<StoreModel> getListStore(){
        return storeDb.findAll();
    }

    @Override
    public StoreModel getStoreByStoreCode(String storeCode){
        return storeDb.findByStoreCode(storeCode);
    }

    @Override
    public StoreModel getStoreByIdStore(Long idStore){
        return storeDb.findById(idStore).get();
    }

    @Override
    public String getStoreCode(StoreModel store) {
        
        String kodeNama = store.getNamaStore().substring(0,2);
        StringBuilder sbKodeNama = new StringBuilder(kodeNama);
        String kodeNamaReversed = sbKodeNama.reverse().toString();

        String storeCode;

        storeCode = "SC" + kodeNamaReversed.toUpperCase();
        return storeCode;
    }
}
