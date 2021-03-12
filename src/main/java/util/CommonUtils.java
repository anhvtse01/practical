package util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Article;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    static Logger logger = LogManager.getLogger(CommonUtils.class);
    private static final String inputFile = "ArticleList.json";

    public static List<Article> getArticlesFromFile() {
        final List<Article> articles = new ArrayList<>();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(inputFile)) {
            final Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            final Gson gson = new Gson();
            List<Article> temp = gson.fromJson(reader, new TypeToken<List<Article>>() {}.getType());
            articles.addAll(temp);
        }
        catch (Exception e) {
            logger.error("Error when get list articles");
        }
        return articles;
    }
}
