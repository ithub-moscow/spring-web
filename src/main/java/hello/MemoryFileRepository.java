package hello;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MemoryFileRepository implements FileRepository {

    Map<String, byte[]> files = new HashMap<>();
    @Override
    public void save(InputStream is, String name) throws IOException {
        ByteArrayOutputStream fos = new ByteArrayOutputStream();
        int b;
        while ((b = is.read()) != -1) {
            fos.write(b);
        }
        fos.close();
        files.put(name, fos.toByteArray());
    }
}
