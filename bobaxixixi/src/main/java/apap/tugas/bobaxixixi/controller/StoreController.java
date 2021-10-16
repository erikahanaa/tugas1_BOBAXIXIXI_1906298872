package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.StoreModel;
import apap.tugas.bobaxixixi.model.ManagerModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.service.StoreService;
import apap.tugas.bobaxixixi.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalTime;
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
        store.setStoreCode(storeService.getStoreCode(store));
        model.addAttribute("storeCode", storeService.getStoreCode(store));
        try {
            storeService.addStore(store);
        } catch (Exception e) {
            return "add-store-failed";
        }
        return "add-store";
    }
 
    @GetMapping("/store")
    public String viewAllStore(
        Model model
    ){
        model.addAttribute("listStore", storeService.getListStore());
        return "viewall-store";
    }

    @GetMapping("/store/{idStore}")
    public String findStoreByIdStore(
        @PathVariable long idStore, Model model){
        StoreModel store = storeService.getStoreByIdStore(idStore);
        List<StoreBobaTeaModel> listStoreBobaTea = store.getListStoreBobaTea();
        model.addAttribute("store", store);
        model.addAttribute("listStoreBobaTea", listStoreBobaTea);
        return "view-store-by-id";
    }

    @GetMapping("/store/update/{idStore}")
    public String updateStoreForm(
        @PathVariable long idStore, Model model) {
        StoreModel store = storeService.getStoreByIdStore(idStore);
        List<ManagerModel> listManager = managerService.getListManager();
        model.addAttribute("store", store);
        model.addAttribute("listManager", listManager);
        return "form-update-store";
    }

    @PostMapping("/store/update")
    public String updateStoreSubmit(
            @ModelAttribute StoreModel store,
            Model model
    ){
        StoreModel storeUpdated = storeService.updateStore(store);
        model.addAttribute("store", storeUpdated);
        model.addAttribute("storeCode", storeService.getStoreCode(store));
        return "update-store";
    }

    @PostMapping("store/delete/{idStore}")
    public String deleteStore(
        @ModelAttribute("store") StoreModel store,
        Model model
    ){
        StoreModel storeGet = storeService.getStoreByIdStore(store.getIdStore());
        if (storeGet == null){
            return "error";
        }
        else{
            LocalTime openHour = storeGet.getOpenHour();
            LocalTime closeHour = storeGet.getCloseHour();
            LocalTime currentTime = LocalTime.now();
            int jumlahBoba = storeGet.getListStoreBobaTea().size();
            
            if ((closeHour.isBefore(currentTime) || openHour.isAfter(currentTime) && (jumlahBoba == 0))){
                storeService.deleteStore(storeGet);
                model.addAttribute("storeCode", storeGet.getStoreCode());
                return "delete-store";
            }

            if ((closeHour.isBefore(currentTime) || openHour.isAfter(currentTime) && (jumlahBoba != 0))){
                model.addAttribute("storeCode", storeGet.getStoreCode());
                return "delete-store-failed-boba";
            }

            model.addAttribute("storeCode", storeGet.getStoreCode());
            return "delete-store-failed";
        }
    }
}
