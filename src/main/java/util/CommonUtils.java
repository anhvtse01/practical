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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    public List<Article> validate(final List<Article> input) {
        return input.stream()
                    .filter(this::isDiscountPriceValid)
                    .collect(Collectors.toList());
    }

    public static Date formatDate(String date, String format) {
        if (date == null || date.equals("")) {
            return null;
        }
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date d = new Date();
        try {
            d = sdf.parse(date);
        }
        catch (ParseException e) {
            logger.error("Can not parse date {}", date);
            return null;
        }
        return d;
    }

    private boolean isDiscountPriceValid(final Article article) {
        return article.getDiscountInfo()
                      .stream()
                      .filter(Objects::nonNull)
                      .anyMatch(a -> a.getDiscountPrice() > article.getNetPrice());
    }
}
