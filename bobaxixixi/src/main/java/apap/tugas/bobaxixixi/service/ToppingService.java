package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.ToppingModel;
import java.util.List;

public interface ToppingService {
    void addTopping(ToppingModel topping);
    List<ToppingModel> getListTopping();
    ToppingModel getToppingByNama(String namaTopping);
}
