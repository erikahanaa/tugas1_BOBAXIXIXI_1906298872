package apap.tugas.bobaxixixi.service;

import apap.tugas.bobaxixixi.model.ManagerModel;
import java.util.List;

public interface ManagerService {
    void addManager(ManagerModel manager);
    List<ManagerModel> getListManager();
}
