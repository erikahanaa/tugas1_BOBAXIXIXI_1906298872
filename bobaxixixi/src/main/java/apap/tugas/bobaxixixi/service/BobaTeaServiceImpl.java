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
    public Optional<BobaTeaModel> getBobaTeaById(Long idBoba){
      return bobaTeaDb.findById(idBoba);
    }

    @Override
    public List<BobaTeaModel> getListBobaTea(){
        return bobaTeaDb.findAll();
    }
}
