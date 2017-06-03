import com.javapatterns.CarSingleton;
import com.javapatterns.PepleSingeton;
import com.javapatterns.Singleton;
import com.javapatterns.SingletonThread;

public class TestSingleton {
    public static void main(String[] args) {
        Singleton.showInfo();
        // Singleton.getInstance();
        System.out.println("###");
        Singleton.getInstance();

        PepleSingeton pepleSingeton1 = PepleSingeton.INSTACE.get();
        PepleSingeton pepleSingeton2 = PepleSingeton.INSTACE.get();
        CarSingleton car1 = CarSingleton.INSTACE.get();
        CarSingleton car2 = CarSingleton.INSTACE.get();

        System.out.println("##" + (pepleSingeton1 == pepleSingeton2));
        System.out.println("car##" + (car1 == car2));

        SingletonThread[] threads = new SingletonThread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new SingletonThread();
        }
        for (SingletonThread thread : threads) {
            thread.start();
        }
    }
}
