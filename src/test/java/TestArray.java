public class TestArray {

    public static void main(String[] args) {
        Object[][] contents = new String[][]{{"ABOUT", "关于"}, {"PRINT", "打印", "dfdddf"}};
        System.out.println("##" + "eeeerr".replace("e", "d"));
        Object[][] obj1 = new Object[10][2];
        for (int i = 0; i < contents.length; i++) {
            for (int j = 0; j < contents[i].length; j++) {
                System.out.println("##" + contents[i][j]);

            }

        }
        for (int i = 0; i < 10; i++) {
            obj1[i] = new String[]{"111", "ssss"};
            for (int j = 0; j < 2; j++) {
                obj1[i][j] = "sdsd";
            }
        }

        for (int i = 0; i < obj1.length; i++) {
            for (int j = 0; j < obj1.length; j++) {
                System.out.println("obj1[i][j]==" + obj1[i][j]);
            }
        }
        String strs[] = new String[]{"sd", "sdsd"};

    }
}
