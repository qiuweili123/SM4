import com.javapatterns.abstractfactory.Factory;
import com.javapatterns.abstractfactory.Fruit;
import com.javapatterns.abstractfactory.RedApple;

public class TestFactory {
    public static void main(String[] args) throws Exception {
        Fruit fruit = Factory.createFruit(RedApple.class);
        fruit.sayClassName();
    }
}
