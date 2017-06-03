import com.java.fetchUrl.GetTitleRunnable;


public class TestFetchURLContent {
    public static void main(String[] args) throws InterruptedException {

        // System.out.print(getTitleRunnable.getTitle("http://www.oschina.net/code/snippet_1417577_48298"));
        String saveDirectory = System.getProperty("user.dir");
        String inFileName = saveDirectory + "\\in.txt";
        String outFileName = saveDirectory + "\\out.txt";
        System.out.println("File==" + inFileName);
        GetTitleRunnable getTitleRunnable = new GetTitleRunnable(inFileName, outFileName);
        getTitleRunnable.MultiThreadsGetTitle(2);
    }
}
