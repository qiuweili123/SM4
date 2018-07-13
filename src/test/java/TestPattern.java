import com.javapatterns.builder.AppleBuilder;
import com.javapatterns.builder.Foo;
import org.junit.Test;


public class TestPattern {
    @Test
    public void testBuilder() throws Exception {

        //DoDoContact d=  DoDoContact.
        AppleBuilder builder = new AppleBuilder.Builder().color("red").build();
        System.out.println("##builder==" + builder.getColor());
        Foo<String> foo=Foo.Builder.init(String.class).key("sdsdddddd").setK("sdsd").build();

        foo.print();

    }
}
