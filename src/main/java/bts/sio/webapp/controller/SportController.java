package bts.sio.webapp.controller;

import bts.sio.webapp.model.Sport;
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
public class SportController {


    @Autowired
    private SportService sportService;

    @GetMapping("/listerSports")
    public String listerSports(Model model) {
        Iterable<Sport> listSports = sportService.getLesSports();
        model.addAttribute("sports", listSports);
        return "listerSports";
    }

    @GetMapping("/createSport")
    public String createSport(Model model) {
        Sport s = new Sport();
        model.addAttribute("sport", s);

        return "sport/formNewSport";
    }

    @GetMapping("/updateSport/{id}")
    public String updateSport(@PathVariable("id") final int id, Model model) {
        Sport s = sportService.getSport(id);
        model.addAttribute("sport", s);


        return "sport/formUpdateSport";
    }

    @GetMapping("/deleteSport/{id}")
    public ModelAndView deleteSport(@PathVariable("id") final int id) {
        sportService.deleteSport(id);
        return new ModelAndView("redirect:/listerSports");
    }

    @PostMapping("/saveSport")
    public ModelAndView saveSport(@ModelAttribute Sport sport) {
        System.out.println("controller save=" + sport.getNom());
        sportService.saveSport(sport);
        return new ModelAndView("redirect:/listerSports");
    }
}
