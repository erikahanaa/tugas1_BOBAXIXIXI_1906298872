package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.repository.BobaTeaDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
 
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BobaTeaServiceImpl implements BobaTeaService{
    @Autowired
    BobaTeaDb bobaTeaDb;

    @Override
    public void addBobaTea(BobaTeaModel bobaTea){
        bobaTeaDb.save(bobaTea);
    }

    @Override
    public BobaTeaModel getBobaTeaById(Long idBoba){
        Optional<BobaTeaModel> bobaTea = bobaTeaDb.findByIdBoba(idBoba);
        if (bobaTea.isPresent()){
            return bobaTea.get();
        } 
        else return null;
    }

    @Override
    public List<BobaTeaModel> getListBobaTea(){
        return bobaTeaDb.findAll();
    }

    @Override
    public BobaTeaModel updateBobaTea(BobaTeaModel bobaTea){
        bobaTeaDb.save(bobaTea);
        return bobaTea;
    }

    @Override
    public void deleteBobaTea(BobaTeaModel bobaTea){
        bobaTeaDb.delete(bobaTea);
    }

    @Override
    public BobaTeaModel getBobaTeaByNama(String namaBoba){
        return bobaTeaDb.findByNamaBoba(namaBoba);
    }
}
