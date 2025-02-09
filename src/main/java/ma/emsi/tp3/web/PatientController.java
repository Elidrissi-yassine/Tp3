package ma.emsi.tp3.web;

import lombok.AllArgsConstructor;
import ma.emsi.tp3.entities.Patient;
import ma.emsi.tp3.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    PatientRepository patientRepository;
    @GetMapping("/index")
    public String index(Model model,
                        @RequestParam(name="page",defaultValue = "0") int page,
                        @RequestParam(name="number",defaultValue = "4")int number,
                        @RequestParam(name="keyword",defaultValue = "") String keyword
    ){
       Page<Patient> pagepatients=patientRepository.findByNomContains(keyword,PageRequest.of(page,number));
        model.addAttribute("list",pagepatients.getContent());
        model.addAttribute("pages",new int[pagepatients.getTotalPages()]);
        model.addAttribute("currentpage",page);
        model.addAttribute("keyword",keyword);
        return "patients";
    }
    @GetMapping("/delete")
    public String delete(Long id,String keyword,int page){
        patientRepository.deleteById(id);
        return "redirect:/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/")
    public String home(){
        return "redirect:/index";
    }
}
