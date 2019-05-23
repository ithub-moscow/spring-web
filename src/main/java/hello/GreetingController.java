package hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.*;

@Controller
@RequestMapping
public class GreetingController {

    private NewsRepository repository;

    @GetMapping(path = "/")
    public String index(Model model) {
        model.addAttribute("name", "Without Name");
        return "index";
    }

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("section", "greeting");
        return "greeting";
    }

    @PostMapping(path = "/accept", consumes = APPLICATION_FORM_URLENCODED_VALUE)
    public String acceptName(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/news")
    public String news(Model model) throws IOException {
        fileToModel("news.txt", model);

        return "news";
    }

    @GetMapping("/news/{file}")
    public String newsFile(Model model,
                           @PathVariable("file") String fileName) throws IOException {
        fileToModel(fileName, model);

        return "news";
    }

    private void fileToModel(String fileName, Model model) throws IOException {
        NewsRepository repository = new FileNewsRepository(
                ClassLoader.getSystemResourceAsStream(fileName));
        List<News> news = repository.readNews();

        model.addAttribute("news", news);
        model.addAttribute("section", "news");
    }

    @RequestMapping(path = "/news/{id}",
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE },
            produces = MediaType.TEXT_HTML_VALUE)
    public String get(@RequestParam("size") Integer size) {
        return "index";
    }

    @PostMapping(consumes = APPLICATION_FORM_URLENCODED_VALUE, path = "/news")
    public void post(@ModelAttribute News body) {
        System.out.println(body);
    }
}
