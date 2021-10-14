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
}
