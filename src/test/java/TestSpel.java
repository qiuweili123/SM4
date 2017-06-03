import com.spring.spel.SpELValue;
import org.junit.Test;


public class TestSpel {
    @Test
    public void testSpElValue() throws Exception {
        SpELValue spELValue = new SpELValue();
        System.out.println("##" + spELValue.language);
    }
}
