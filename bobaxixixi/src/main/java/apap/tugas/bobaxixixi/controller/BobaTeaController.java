package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.ToppingModel;
import apap.tugas.bobaxixixi.model.StoreBobaTeaModel;
import apap.tugas.bobaxixixi.model.ManagerModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.ToppingService;
import apap.tugas.bobaxixixi.service.StoreBobaTeaService;
import apap.tugas.bobaxixixi.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BobaTeaController {
    
    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;

    @Qualifier("storeBobaTeaServiceImpl")
    @Autowired
    private StoreBobaTeaService storeBobaTeaService;

    @Qualifier("managerServiceImpl")
    @Autowired
    private ManagerService managerService;
    
    @GetMapping("/boba")
    public String viewAllBobaTea(
        Model model
    ){
        model.addAttribute("listBobaTea", bobaTeaService.getListBobaTea());
        return "viewall-boba-tea";
    }

    @GetMapping("/boba/add")
    public String addBobaTeaForm(Model model){

        List<ToppingModel> listTopping = toppingService.getListTopping();

        model.addAttribute("bobaTea", new BobaTeaModel());
        model.addAttribute("listTopping", listTopping);
       return "form-add-boba-tea";
    }

    @PostMapping("/boba/add")
    public String addBobaTeaSubmit(
            @ModelAttribute BobaTeaModel bobaTea,
            Model model
    ){
        bobaTeaService.addBobaTea(bobaTea);
        model.addAttribute("namaBoba", bobaTea.getNamaBoba());
        return "add-boba-tea";
    }

    @GetMapping("/boba/{idBoba}")
    public String findBobaTeaByIdBoba(
        @PathVariable Long idBoba, Model model){
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaById(idBoba);
        if (bobaTea == null || idBoba == null){
            return "no-boba";
        }
        model.addAttribute("bobaTea", bobaTea);
        return "view-boba-tea-by-id";
    }

    @GetMapping("/boba/update/{idBoba}")
    public String updateBobaForm(
        @PathVariable Long idBoba, Model model) {
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaById(idBoba);
        List<ToppingModel> listTopping = toppingService.getListTopping();
        int jumlahBoba = bobaTea.getListStoreBobaTea().size();
        
        if (bobaTea == null || idBoba == null){
            return "no-boba";
        }
        
        if (jumlahBoba == 0){
            model.addAttribute("bobaTea", bobaTea);
            model.addAttribute("listTopping", listTopping);
            return "form-update-boba";
        }
        model.addAttribute("namaBoba", bobaTea.getNamaBoba());
        return "update-boba-failed";
    }

    @PostMapping("/boba/update")
    public String updateBobaSubmit(
            @ModelAttribute BobaTeaModel bobaTea,
            Model model
    ){
        BobaTeaModel bobaTeaUpdated = bobaTeaService.updateBobaTea(bobaTea);
        model.addAttribute("namaBoba", bobaTeaUpdated.getNamaBoba());
        return "update-boba-tea";
    }

    @PostMapping("boba/delete/{idBoba}")
    public String deleteBobaTea(
        @ModelAttribute("bobaTea") BobaTeaModel bobaTea,
        Model model
    ){
        BobaTeaModel bobaGet = bobaTeaService.getBobaTeaById(bobaTea.getIdBoba());
        int jumlahBoba = bobaGet.getListStoreBobaTea().size();
        if (jumlahBoba == 0){
            model.addAttribute("namaBoba", bobaGet.getNamaBoba());
            bobaTeaService.deleteBobaTea(bobaGet);
            return "delete-boba-tea";
        }
        model.addAttribute("namaBoba", bobaGet.getNamaBoba());
        return "delete-boba-tea-failed";
    }

    @RequestMapping(value = "search", method= RequestMethod.GET)
    public String searchBobaNull(Model model) {

        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        model.addAttribute("listBobaTea", listBobaTea);

        List <ToppingModel> listTopping = toppingService.getListTopping();
        model.addAttribute("listTopping", listTopping);
        return "search-boba";
    }

    @RequestMapping(value="search" , params= {"namaBoba","namaTopping"},  method= RequestMethod.GET)
    public String searchBoba(
            @RequestParam(required = false, value = "namaBoba") String namaBoba,
            @RequestParam(required = false, value = "namaTopping") String namaTopping,
            Model model){
        
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        List<ToppingModel> listTopping = toppingService.getListTopping();
        List<StoreBobaTeaModel> listStoreBobaTea = storeBobaTeaService.getListStoreBobaTea();
        
        if (namaBoba != null && namaTopping != null){
            List<StoreBobaTeaModel> listStoreFix = new ArrayList<>();
            for (StoreBobaTeaModel storeBobaTea: listStoreBobaTea){
                BobaTeaModel bobaGet = storeBobaTea.getBobaTea();
                LocalTime openHour = storeBobaTea.getStore().getOpenHour();
                LocalTime closeHour = storeBobaTea.getStore().getCloseHour();
                LocalTime currentTime = LocalTime.now();

                if (bobaGet.getNamaBoba().equals(namaBoba)){
                    if (bobaGet.getTopping().getNamaTopping().equals(namaTopping)){
                        if ((closeHour.isAfter(currentTime) || openHour.isBefore(currentTime))){
                            listStoreFix.add(storeBobaTea);
                        }
                    }
                }
            }
            model.addAttribute("listBobaTea", listBobaTea);
            model.addAttribute("listTopping", listTopping);
            model.addAttribute("listStoreFix", listStoreFix);
            return "search-boba";
        }
        return "search-boba-failed";
    }

    @RequestMapping(value = "filter", method= RequestMethod.GET)
    public String filterManagerNull(Model model) {

        List <BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        model.addAttribute("listBobaTea", listBobaTea);

        List <ManagerModel> listManager = managerService.getListManager();
        model.addAttribute("listManager", listManager);

        return "filter-manager";
    }

    @RequestMapping(value="search" , params= {"namaBoba"},  method= RequestMethod.GET)
    public String filterManager(
            @RequestParam(required = false, value = "namaBoba") String namaBoba,
            Model model){
        
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        List<ManagerModel> listManager = managerService.getListManager();
        List<StoreBobaTeaModel> listStoreBobaTea = storeBobaTeaService.getListStoreBobaTea();
        
        if (namaBoba != null){
            List<StoreBobaTeaModel> listStoreFix = new ArrayList<>();
            for (StoreBobaTeaModel storeBobaTea: listStoreBobaTea){
                BobaTeaModel bobaGet = storeBobaTea.getBobaTea();
                if (bobaGet.getNamaBoba().equals(namaBoba)){
                    listStoreFix.add(storeBobaTea);
                }
            }
            model.addAttribute("listBobaTea", listBobaTea);
            model.addAttribute("listManager", listManager);
            model.addAttribute("listStoreFix", listStoreFix);
            return "filter-manager";
        }
        return "filter-manager-failed";
    }

}
