package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private FileRepository repository;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String load(HttpServletRequest request) throws IOException, ServletException {
        Part file = request.getPart("file");
        InputStream is = file.getInputStream();
        repository.save(is, file.getSubmittedFileName());
        return "index";
    }

    @GetMapping(path = "{fileName}")
    public InputStream get(@PathVariable("fileName") String fileName) {
        return repository.read(fileName);
    }
}
