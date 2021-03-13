import model.Article;
import model.DiscountInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.CommonUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MainClass {
    static Logger logger = LogManager.getLogger(CommonUtils.class);

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.print("Input discount date: \n");
            final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            final String date = br.readLine();
            final Optional<Date> discountDate = CommonUtils.formatDate(date);
            final List<Article> articles = CommonUtils.getArticlesFromFile();
            if (!articles.isEmpty() && discountDate.isPresent()) {
                logger.info("Article not found or date input invalid");
                if (!printArticleDiscount(articles, discountDate.get()).isEmpty()) {
                    break;
                }
                System.out.print("Not found any discount article. Please input another date \n");
            }
        }

    }

    public static List<Double> printArticleDiscount(final List<Article> articleList, final Date inputDate) {
        final List<Double> result = new ArrayList<>();
        for (Article article : articleList) {
            final List<DiscountInfo> discountInfos = article.getDiscountInfo();
            for (DiscountInfo discount : discountInfos) {
                if (discount != null && discount.getDiscountDate() != null && discount.getDiscountPercent() != null) {
                    // Compare date
                    final Optional<Date> articleDiscountDate = CommonUtils.formatDate(discount.getDiscountDate());
                    if (articleDiscountDate.isPresent()) {
                        final boolean isValidDate = inputDate.equals(articleDiscountDate.get());
                        if (isValidDate) {
                            final double discountPrice = article.getSalePrice() - (article.getSalePrice() * discount.getDiscountPercent());
                            if (discountPrice > article.getNetPrice()) {
                                System.out.print("Article name: " + article.getName() + "\n");
                                System.out.print("Article slogan: " + article.getSlogan() + "\n");
                                System.out.print("Discount price: " + discountPrice + "\n");
                                System.out.print("--------------------- \n");
                                result.add(discountPrice);
                                break;
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
