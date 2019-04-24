package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.net.URI;

@Controller
@RequestMapping("/files")
public class FilesController {

    @Autowired
    private FileRepository repository;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity load(HttpServletRequest request) throws IOException, ServletException {
        Part file = request.getPart("file");
        InputStream is = file.getInputStream();
        repository.save(is, file.getSubmittedFileName());
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .location(URI.create("/files"))
                .build();
    }

    @ResponseBody
    @GetMapping(path = "{fileName}", produces = "application/octet-stream")
    public ResponseEntity<byte[]> get(@PathVariable("fileName") String fileName) throws IOException {
        try {
            InputStream is = repository.read(fileName);
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
            return ResponseEntity.ok(os.toByteArray());
        } catch (FileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("files", repository.list());
        return "files/list";
    }
}
