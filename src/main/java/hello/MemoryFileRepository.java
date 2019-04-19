package hello;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

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
        files.put(name, fos.toByteArray());
        fos.close();
    }

    @Override
    public InputStream read(String name) throws FileNotFoundException {
        throw new NotImplementedException();
    }
}
