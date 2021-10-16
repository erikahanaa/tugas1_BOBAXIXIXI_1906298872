package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.ToppingModel;
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
        storeBobaTea.setProductionCode(storeBobaTea.getProductionCode());
        storeBobaTeaDb.save(storeBobaTea);
    }

    @Override
    public List<StoreBobaTeaModel> getListStoreBobaTea(){
        return storeBobaTeaDb.findAll();
    }

    @Override
    public String getProductionCode(StoreBobaTeaModel storebobaTea) {
        Long idStoreAsli = storebobaTea.getStore().getIdStore();
        String idStore = String.format("%03d", idStoreAsli);
        ToppingModel topping = storebobaTea.getBobaTea().getTopping();
        Integer punyaTopping;
        if (topping != null){
            punyaTopping = 1;
        }
        else{
            punyaTopping = 0;
        }
        Long idBobaTeaAsli = storebobaTea.getBobaTea().getIdBoba();
        String idBobaTea = String.format("%03d", idBobaTeaAsli);
        String productionCode;
        productionCode = "PC" + idStore + punyaTopping + idBobaTea;
        return productionCode;
    }

    @Override
    public void deleteStoreBobaTea(StoreBobaTeaModel storeBobaTea){
        storeBobaTeaDb.delete(storeBobaTea);
    }
}
