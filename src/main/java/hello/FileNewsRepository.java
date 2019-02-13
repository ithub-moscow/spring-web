package hello;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;

public class FileNewsRepository implements NewsRepository {

    private InputStream source;

    public FileNewsRepository(InputStream source) {
        this.source = source;
    }

    @Override
    public List<News> readNews() throws IOException {
        List<News> news = new ArrayList<>();

        LineNumberReader reader = new LineNumberReader(new InputStreamReader(source));

        String title;
        while ((title = reader.readLine()) != null) {
            String s, content = "";
            while (true) {
                s = reader.readLine();
                if(s == null || s.equals(""))
                    break;
                content = content + s;
            }
            News oneNews = new News(title, content);
            news.add(oneNews);
        }

        return news;
    }
}
