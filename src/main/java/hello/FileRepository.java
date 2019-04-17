package hello;

import java.io.IOException;
import java.io.InputStream;

public interface FileRepository {
    void save(InputStream is, String name) throws IOException;
    InputStream read(String name);

}
