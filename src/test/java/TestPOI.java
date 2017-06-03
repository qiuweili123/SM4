import org.epe.core.ExcelResolve;
import org.epe.core.ResolveException;
import org.epe.core.SheetItem;
import org.epe.core.test.Student;
import org.epe.core.test.Teacher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestPOI {
    private static String LIST_KEY = "list";
    private static String MESSAGE_KEY = "message";

    public static void main(String[] args) throws ResolveException {
        TestPOI test = new TestPOI();
//		test.reader();
        test.write();
        System.out.println("-----------------------------------wirte finish ----------");
    }

    public void write() {
        List<Student> list = new ArrayList<Student>();
        List<Teacher> list1 = new ArrayList<Teacher>();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 900000; i++) {
            Student su = new Student();
            int id = i + 1;
            su.setName("张三" + id);
            su.setAge(1 + i);
            su.setSex("男");
            su.setSid(id);
            list.add(su);
        }
//		for(int j=0;j<65530;j++)
//		{
//			Teacher teacher=new Teacher();
//			teacher.setName("李四"+j);
//			teacher.setEducation("将军");
//			list1.add(teacher);
//		}

        // 实例化ERE组件
        ExcelResolve ope = new ExcelResolve();
        File file = new File("D:\\老年Excel.xls");
//		 if(!file.exists()){
//			 try {
//				file.createNewFile();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		 }
        ope.setServerPath(file.getAbsolutePath());
        // 设置sheet信息
        SheetItem sheetItem = new SheetItem();
        sheetItem.setName("学生");
        sheetItem.setList(list);

        SheetItem sheetItem1 = new SheetItem();
        sheetItem1.setName("老师");
        sheetItem1.setList(list1);
        // 设置一个sheetItem
        ope.exportExcel(sheetItem, sheetItem1);
        long end = System.currentTimeMillis();

        System.out.println((end - start) / 1000);
    }

    public void reader() {
        // 实例化ERE组件
        ExcelResolve ope = new ExcelResolve();
        ope.setServerPath("c:\\老年人口信息Excel.xls");
        ope.readerDataSource();
        List<SheetItem> sheets = ope.inputExcel();
        //得到所有的sheetItem
        for (SheetItem sheetItem : sheets) {

            if ("学生".equals(sheetItem.getName())) {
                Map map = ope.excelObjectMapping(sheetItem.getHeadCells(), Student.class);
                //得到EOM后的集合
                List<Student> list = (List<Student>) map.get(TestPOI.LIST_KEY);
                //得到异常信息
                List<StringBuffer> message = (List<StringBuffer>) map.get(TestPOI.MESSAGE_KEY);
                for (int i = 0; i < list.size(); i++) {
                    Student student = list.get(i);
                    if (student.getName() != null && !"".equals(student.getName())) {
                        System.out.println("姓名：" + student.getName());
                        System.out.println("年龄：" + student.getAge());
                    }
                }
            } else {
                Map map = ope.excelObjectMapping(sheetItem.getHeadCells(), Teacher.class);
                //得到EOM后的集合
                List<Teacher> list1 = (List<Teacher>) map.get(TestPOI.LIST_KEY);
                //得到异常信息
                List<StringBuffer> message = (List<StringBuffer>) map.get(TestPOI.MESSAGE_KEY);
                for (int i = 0; i < list1.size(); i++) {
                    Teacher teacher = list1.get(i);
                    if (teacher.getName() != null && !"".equals(teacher.getName())) {
                        System.out.println("姓名：" + teacher.getName());
                        System.out.println("学历：" + teacher.getEducation());

                    }
                }
            }
        }
    }
}
