package hello;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FilesController {

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String load(HttpServletRequest request) throws IOException, ServletException {
        Part file = request.getPart("file");
        return "index";
    }
}
