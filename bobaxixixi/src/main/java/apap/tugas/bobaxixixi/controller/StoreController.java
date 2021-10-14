package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.model.ManagerModel;
import apap.tugas.bobaxixixi.service.StoreService;
import apap.tugas.bobaxixixi.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;

@Controller
public class StoreController {

    @Qualifier("storeServiceImpl")
    @Autowired
    private StoreService storeService;

    @Qualifier("managerServiceImpl")
    @Autowired
    private ManagerService managerService;
    
 
    @GetMapping("/store/add")
    public String addStoreForm(Model model){

        List<ManagerModel> listManager = managerService.getListManager();

        model.addAttribute("store", new StoreModel());
        model.addAttribute("listManager", listManager);
        return "form-add-store";
    }

    @PostMapping("/store/add")
    public String addStoreSubmit(
            @ModelAttribute StoreModel store,
            Model model
    ){
        storeService.addStore(store);
        model.addAttribute("idStore", store.getIdStore());
        return "add-store";
    }
 
    @GetMapping("/store")
    public String viewAllStore(
        Model model
    ){
        model.addAttribute("listStore", storeService.getListStore());
        return "viewall-store";
    }
}
