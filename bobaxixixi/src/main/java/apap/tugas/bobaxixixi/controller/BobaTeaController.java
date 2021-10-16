package apap.tugas.bobaxixixi.controller;

import apap.tugas.bobaxixixi.model.BobaTeaModel;
import apap.tugas.bobaxixixi.model.ToppingModel;
import apap.tugas.bobaxixixi.service.BobaTeaService;
import apap.tugas.bobaxixixi.service.ToppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

@Controller
public class BobaTeaController {
    
    @Qualifier("bobaTeaServiceImpl")
    @Autowired
    private BobaTeaService bobaTeaService;

    @Qualifier("toppingServiceImpl")
    @Autowired
    private ToppingService toppingService;
    
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
        @PathVariable long idBoba, Model model){
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaById(idBoba);
        model.addAttribute("bobaTea", bobaTea);
        return "view-boba-tea-by-id";
    }

    @GetMapping("/boba/update/{idBoba}")
    public String updateBobaForm(
        @PathVariable long idBoba, Model model) {
        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaById(idBoba);
        List<ToppingModel> listTopping = toppingService.getListTopping();
        model.addAttribute("bobaTea", bobaTea);
        model.addAttribute("listTopping", listTopping);
        return "form-update-boba";
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
        if (bobaGet == null){
            return "error";
        }
        model.addAttribute("namaBoba", bobaGet.getNamaBoba());
        bobaTeaService.deleteBobaTea(bobaGet);
        return "delete-boba-tea";
    }

    @GetMapping(value = "/search/")
    public String searchBobaNull(Model model) {

        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        model.addAttribute("listBobaTea", listBobaTea);

        List <ToppingModel> listTopping = toppingService.getListTopping();
        model.addAttribute("listTopping", listTopping);
        return "search-boba";
    }

    @GetMapping(value="/search/" , params= {"namaBoba","namaTopping"})
    public String searchBoba(
            @RequestParam(required = false, value = "namaBoba") String namaBoba,
            @RequestParam(required = false, value = "namaTopping") String namaTopping,
            Model model){

        BobaTeaModel bobaTea = bobaTeaService.getBobaTeaByNama(namaBoba);
        ToppingModel topping = toppingService.getToppingByNama(namaTopping);
        List<BobaTeaModel> listBobaTea = bobaTeaService.getListBobaTea();
        List<ToppingModel> listTopping = toppingService.getListTopping();
        
        if (bobaTea == null || namaBoba == null){
            return "search-failed-boba";
        }
        if (topping == null || namaTopping == null){
            return "search-failed-topping";
        }
        model.addAttribute("listBobaTea", listBobaTea);
        model.addAttribute("listTopping", listTopping);
        return "search-boba";
    }

}
