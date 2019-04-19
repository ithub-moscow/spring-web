package hello;

import org.springframework.beans.factory.annotation.Value;

import java.io.*;

public class StorageFileRepository implements FileRepository {

    @Value("${storage.path}")
    private String path;

    @Override
    public void save(InputStream is, String name) throws IOException {
        FileOutputStream fos = new FileOutputStream(path + "/" + name);
        int b;
        while ((b = is.read()) != -1) {
            fos.write(b);
        }
        fos.close();
    }

    @Override
    public InputStream read(String name) throws FileNotFoundException {
        return new FileInputStream(path + "/" + name);
    }
}
