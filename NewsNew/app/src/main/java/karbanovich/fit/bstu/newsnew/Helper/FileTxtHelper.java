package karbanovich.fit.bstu.newsnew.Helper;

import android.content.Context;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import karbanovich.fit.bstu.newsnew.Model.NewsHeadlines;

public class FileTxtHelper {

    public static File createNewsFile(Context context, NewsHeadlines newsHeadlines, String category) {

        File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), "news.txt");
        String message = "Источник: " + checkNull(newsHeadlines.getSource().getName()) + "\n"
                + "Автор(ы): " + checkNull(newsHeadlines.getAuthor()) + "\n"
                + "Время публикации: " + checkNull(newsHeadlines.getPublishedAt()) + "\n"
                + "Категория: " + category + "\n"
                + "Заголовок: " + checkNull(newsHeadlines.getTitle()) + "\n"
                + "Описание: " + checkNull(newsHeadlines.getDescription()) + "\n"
                + "Ссылка на изображение: " + checkNull(newsHeadlines.getUrlToImage()) + "\n"
                + "Ссылка на оригинал статьи: " + checkNull(newsHeadlines.getUrl()) + "\n"
                + "Контент: " + checkNull(newsHeadlines.getContent()) + " (ЧИТАТЬ ДАЛЬШЕ ПО ССЫЛКЕ)";

        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print("");
            writer.println(message);
            writer.flush();
            writer.close();
            return file;
        } catch (Exception e) {
            Toast.makeText(context, "Ошибка создания файла", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        return file;
    }

    private static String checkNull(String str) {
        if(str == null) return "-";
        else return str;
    }
}
