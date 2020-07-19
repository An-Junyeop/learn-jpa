package util;

import com.mysema.query.annotations.QueryDelegate;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.StringPath;
import model.Item;
import model.QItem;

public class ItemExpression {

    @QueryDelegate(Item.class)
    public static BooleanExpression isExpensive(QItem item, Integer price) {
        return item.price.gt(price);
    }

    @QueryDelegate(String.class)
    public static BooleanExpression isTestStart(StringPath stringPath) {
        return stringPath.startsWith("Test");
    }
}
