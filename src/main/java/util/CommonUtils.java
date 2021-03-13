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
import java.util.*;
import java.util.stream.Collectors;

public class CommonUtils {

    static Logger logger = LogManager.getLogger(CommonUtils.class);
    private static final String INPUT_FILE = "ArticleList.json";
    private static final String FORMAT_DATE = "dd-mm-yyyy";

    public static List<Article> getArticlesFromFile() {
        final List<Article> articles = new ArrayList<>();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(INPUT_FILE)) {
            final Reader reader = new InputStreamReader(in, StandardCharsets.UTF_8);
            final Gson gson = new Gson();
            List<Article> temp = gson.fromJson(reader, new TypeToken<List<Article>>() {
            }.getType());
            articles.addAll(validate(temp));
        } catch (Exception e) {
            logger.error("Error when get list articles", e);
        }
        return articles;
    }

    public static List<Article> validate(final List<Article> input) {
        return input.stream()
                .filter(CommonUtils::isDiscountPriceValid)
                .collect(Collectors.toList());
    }

    public static Optional<Date> formatDate(String date) {
        if (date == null || date.equals("")) {
            return Optional.empty();
        }
        final SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_DATE);
        Date d = new Date();
        try {
            d = sdf.parse(date);
        } catch (ParseException e) {
            logger.error("Date input invalid {}", date);
            return Optional.empty();
        }
        return Optional.of(d);
    }

    private static boolean isDiscountPriceValid(final Article article) {
        return article.getSalePrice() >= article.getNetPrice();
    }
}
