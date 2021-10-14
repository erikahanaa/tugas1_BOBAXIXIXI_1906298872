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

import java.util.List;

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
        return "add-boba-tea";
    }
}
