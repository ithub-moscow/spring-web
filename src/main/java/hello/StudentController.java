package hello;

import hello.data.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/students")
public class StudentController {

    @Autowired
    private StudentRepository repository;

    @GetMapping
    private String list(Model model) {
        model.addAttribute("students", repository.findAll());
        return "students";
    }
}
