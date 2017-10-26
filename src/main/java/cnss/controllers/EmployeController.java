package cnss.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cnss.models.Employe;
import cnss.services.EmployeService;

@Controller
public class EmployeController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeController.class);
	
	
	  @Autowired
	  private EmployeService employeService;
	  
	  @RequestMapping("/listes")
	  public String listEmploye(Model model) {
		  
		  logger.debug("listEmploye() pour obtenir la liste des employés");
		  
		  List<Employe> listEmploye = employeService.findAll();
		  
		  logger.debug("le premier employé : " + listEmploye.get(0).toString());
		  
		  model.addAttribute("employes", listEmploye);
        return "displayliste";
	  }
	   
	   @GetMapping("/form")
	    public String employeForm(Model model) {
	        model.addAttribute("employe", new Employe());
	        return "formulaire";
	    }
	   
	   @PostMapping("/enregister")
	    public String checkEmployeInfo(@Valid Employe employe, BindingResult bindingResult) {

	        if (bindingResult.hasErrors()) {
	            return "formulaire";
	        }

	        employeService.save(employe);
			//logger.debug("Enregister un employé => ");
			//return new ModelAndView("redirect:/listes");
	        return "redirect:/listes";
	    }
	   
	 
	  

}
