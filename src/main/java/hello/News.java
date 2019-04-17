package hello;

import java.util.Objects;

public class News {
    private String title;
    private String text = "";

    public News() {
    }

    public News(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addLine(String text) {
        if(text != null)
            this.text += text + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(title, news.title) &&
                Objects.equals(text, news.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text);
    }

    @Override
    public String toString() {
        return "News{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
