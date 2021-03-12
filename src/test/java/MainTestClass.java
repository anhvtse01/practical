import model.Article;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.CommonUtils;

import java.util.List;

public class MainTestClass {

    @Test
    public void testMain() {
        final List<Article> list = CommonUtils.getArticlesFromFile();
        Assertions.assertEquals(list.size(),2);
    }
}
