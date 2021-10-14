package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.repository.StoreBobaTeaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class StoreBobaTeaServiceImpl implements StoreBobaTeaService{
    @Autowired
    StoreBobaTeaDb storeBobaTeaDb;

    @Override
    public void addStoreBobaTea(StoreBobaTeaModel storeBobaTea){
        storeBobaTeaDb.save(storeBobaTea);
    }

    @Override
    public List<StoreBobaTeaModel> getListStoreBobaTea(){
        return storeBobaTeaDb.findAll();
    }
}
