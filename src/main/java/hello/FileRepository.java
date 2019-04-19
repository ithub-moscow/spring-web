package hello;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileRepository {
    void save(InputStream is, String name) throws IOException;
    InputStream read(String name) throws FileNotFoundException;
    List<String> list();
    void delete(String name);
}
