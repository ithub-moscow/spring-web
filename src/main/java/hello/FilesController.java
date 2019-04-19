package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

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
    public void get(@PathVariable("fileName") String fileName, HttpServletResponse resp) throws IOException {
        InputStream is = repository.read(fileName);
        OutputStream os = resp.getOutputStream();
        int b;
        while ((b = is.read()) != -1) {
            os.write(b);
        }
        resp.setHeader("Content-Type", "application/octet-stream");
    }
}
