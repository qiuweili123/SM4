import java.util.concurrent.atomic.AtomicInteger;


public class TestAtomicInteger {
    public static void main(String[] args) {
        AtomicInteger cnt = new AtomicInteger(0);
        System.out.println(cnt.get());
        cnt.set(8);
        System.out.println("@@" + cnt.getAndDecrement());
        System.out.println(cnt.get());
    }
}
