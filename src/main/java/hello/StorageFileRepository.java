package hello;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StorageFileRepository implements FileRepository {
    @Override
    public void save(InputStream is, String name) throws IOException {
        FileOutputStream fos = new FileOutputStream(name);
        int b;
        while ((b = is.read()) != -1) {
            fos.write(b);
        }
        fos.close();
    }
}
