import groovy.transform.ASTTest;
import org.junit.Test;

import java.text.DecimalFormat;

public class DoubleTest {

    @Test
    public void test(){

        double quanity = 6.0;
        double price = 2.15;
        double total = price*quanity;
        System.out.println(total);

        DecimalFormat df = new DecimalFormat("#.##");
        System.out.println(Double.valueOf(df.format(total).replace(',','.')));
    }
}
