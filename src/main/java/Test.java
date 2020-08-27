import org.antlr.v4.runtime.ParserRuleContext;
import org.apache.spark.sql.catalyst.parser.SqlBaseParser;

/**
 * Author:BY
 * Date:2020/4/14
 * Description:测试
 */
public class Test {
    public static void main(String[] args) {
        System.out.println("Hello World");
        ParserRuleContext empty = SqlBaseParser.CreateHiveTableContext.EMPTY;
    }
}
