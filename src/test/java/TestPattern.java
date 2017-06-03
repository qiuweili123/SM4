import com.javapatterns.builder.AppleBuilder;
import org.junit.Test;


public class TestPattern {
    @Test
    public void testBuilder() throws Exception {

        //DoDoContact d=  DoDoContact.
        AppleBuilder builder = new AppleBuilder.Builder().color("red").build();
        System.out.println("##builder==" + builder.getColor());

    }
}
