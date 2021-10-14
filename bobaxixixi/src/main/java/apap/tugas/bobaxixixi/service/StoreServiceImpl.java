package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.repository.StoreDb;

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
        
        String kodeNama = store.getNamaStore().substring(0, 3);
        StringBuilder sbKodeNama = new StringBuilder(kodeNama);
        String kodeNamaReversed = sbKodeNama.reverse().toString();
        String openHour = store.getOpenHour().toString().substring(0, 2);
        Integer closeHour = store.getCloseHour().getHour();
        Integer closeHourFix = Math.floorDiv(closeHour, 10);
        String random = RandomString.getRandomString(2);
        String storeCode;

        storeCode = "SC" + kodeNamaReversed.toUpperCase() + openHour + closeHourFix + random;
        return storeCode;
    }

    public static class RandomString {
        static String getRandomString(int n)
        {
            String randomString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder sb = new StringBuilder(n);
            for (int i = 0; i < n; i++) {
                int index = (int)(randomString.length() * Math.random());
                sb.append(randomString.charAt(index));
            }
            return sb.toString();
        }
    }

    @Override
    public StoreModel updateStore(StoreModel store){
        store.setStoreCode(getStoreCode(store));
        storeDb.save(store);
        return store;
    }

    @Override
    public void deleteStore(StoreModel store){
        storeDb.delete(store);
    }

}

