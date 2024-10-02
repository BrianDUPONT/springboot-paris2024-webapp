package bts.sio.webapp.controller;

import bts.sio.webapp.model.Actualite;
import bts.sio.webapp.model.Epreuve;
import bts.sio.webapp.model.Sport;
import bts.sio.webapp.service.ActualiteService;
import bts.sio.webapp.service.EpreuveService;
import bts.sio.webapp.service.SportService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Data
@Controller
public class ActualiteController {


    @Autowired
    private ActualiteService actualiteService;

    @Autowired
    private EpreuveService epreuveService;

    @Autowired
    private SportService sportService;

    @GetMapping("/listerActualites")
    public String listerActualites(Model model) {
        Iterable<Actualite> listActualites = actualiteService.getActualites();
        model.addAttribute("actualites", listActualites);
        return "listerActualites";
    }

    @GetMapping("/deleteActualite/{id}")
    public ModelAndView deleteActualite(@PathVariable("id") final int id) {
        actualiteService.deleteActualite(id);
        return new ModelAndView("redirect:/listerActualites");
    }

    @PostMapping("/saveActualite")
    public ModelAndView saveActualite(@ModelAttribute Actualite actualite) {
        System.out.println("controller save=" + actualite.getTitre());
        actualiteService.saveActualite(actualite);
        return new ModelAndView("redirect:/listerActualites");
    }
}
