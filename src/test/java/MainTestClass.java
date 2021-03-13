import model.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.CommonUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MainTestClass {

    @Test
    public void testMain() {
        final List<Article> list = CommonUtils.getArticlesFromFile();
        final String date = "14-03-2021";
        final Optional<Date> discountDate = CommonUtils.formatDate(date);
        final List<Double> result = MainClass.printArticleDiscount(list, discountDate.get());
        Assertions.assertEquals(list.size(), 2);
        Assertions.assertEquals(result.size(), 2);
        Assertions.assertEquals(result.get(0), 8.4);
        Assertions.assertEquals(result.get(1), 12.0);
    }
}
