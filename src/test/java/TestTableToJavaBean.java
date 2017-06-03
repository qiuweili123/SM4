import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class TestTableToJavaBean {

    private static final String LINE = "\r\n";
    private static final String TAB = "\t";
    private static final String TABLE_SPLIT = "_";
    private static final String CON_URL = "jdbc:oracle:thin:@(description=(ADDRESS_LIST=(address=(protocol=tcp)(host=10.1.2.103)(port=1521))(address=(protocol=tcp)(host=10.1.2.104)(port=1521))(load_balance=yes))(connect_data=(server=dedicated)(service_name=erpdb)(failover_mode=(type=session)(method=basic)(retries=5)(delay=15))))";
    private static final String DB_OWNER = "erp_order";
    private static final String DB_PWD = "erp_order";
    private static final String AUTHOR = "liqiuwei";
    private static final String VERSION = "1.0";
    private static final String OUT_PATH = "d:/javabean";
    private static final String[] DEL_PREFIX_TABLE = new String[]{"T_SR_"};
    private static final String SUB_CLASS_FIX = "Vo";//类名后缀
    private static Map<String, String> map;

    static {

        map = new HashMap<String, String>();
        map.put("VARCHAR2", "String");
        map.put("NUMBER(20)", "Long");
        map.put("NUMBER(12,4)", "Double");
        map.put("FLOAT", "Float");
        map.put("TIMESTAMP", "Date");
        map.put("CHAR", "String");
        map.put("DATETIME", "Date");
        map.put("DATE", "Date");
        map.put("TIMESTAMP_IMPORT", "import java.util.Date");
        map.put("Date_IMPORT", "import java.util.Date");
        map.put("Double_IMPORT", "import java.lang.Double");
        map.put("INT", "int");
        map.put("SMALLINT", "int");
    }

    String packages = "com.inth.erp.service.base.oms.vo";// = this.getClass().getPackage().getName().replace("common", "model");;

    public static String getPojoType(String dataType) {
        StringTokenizer st = new StringTokenizer(dataType);
        return map.get(st.nextToken());
    }

    public static String getImport(String dataType) {
        if (map.get(dataType) == null || "".equals(map.get(dataType))) {
            return null;
        } else {
            return map.get(dataType);
        }
    }

    public static void main(String[] args) throws Exception {
        //String jdbcString = "jdbc:oracle:thin:@(description=(ADDRESS_LIST=(address=(protocol=tcp)(host=10.1.2.103)(port=1521))(address=(protocol=tcp)(host=10.1.2.104)(port=1521))(load_balance=yes))(connect_data=(server=dedicated)(service_name=erpdb)(failover_mode=(type=session)(method=basic)(retries=5)(delay=15))))";
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con = DriverManager.getConnection(CON_URL, DB_OWNER, DB_PWD);
        DatabaseMetaData databaseMetaData = con.getMetaData();
        String[] tableType = {"TABLE"};
        String[] tableNames = {"T_SR_EXCHANGE_REASON", "T_SR_EXCHANGE_LOG", "T_SR_EXCHANGE_REJECT_ITEM", "T_SR_EXCHANGE_REISSUE_ITEM", "T_SR_CARGO", "T_SR_CARGO_ITEM", "T_SR_EXCHANGE_BLANK_RELA"};
        //for(String tName:tableNames){
        ResultSet rs = databaseMetaData.getTables(null, null, "T_SR_%", tableType);
        TestTableToJavaBean d = new TestTableToJavaBean();
        while (rs.next()) {
            String tableName = rs.getString("TABLE_NAME").toString();
            System.out.println("tableName=" + tableName);
            d.tableToBean(con, tableName);
        }
        //}

    }

    /***
     * 打印MySql的表模板参数文件(jsp):
     * @throws Exception
     * @throws UnsupportedEncodingException
     */
    //    public static void sysoutMySqlTCloumns(String Table) throws SQLException, UnsupportedEncodingException{
    //       // getMySqlConnection();
    //        //System.setProperty("file.encoding", "UTF-8");
    //        List<HashMap<String,String>> columns = new ArrayList<HashMap<String,String>>();
    //        try{
    //            Statement stmt = con.createStatement();
    //            String sql = " show full columns from "+Table;
    //            System.out.println(sql);
    //            ResultSet rs = stmt.executeQuery(sql);
    //            while (rs.next()){
    //                HashMap<String,String> map = new HashMap<String,String>();
    //                String Comment = rs.getString("Comment");
    //                map.put("Name",Comment);
    //                map.put("Code", rs.getString("Field"));
    //                map.put("DataType", rs.getString("Type"));
    //                map.put("Comment", rs.getString("Comment")); //varchar int text datetime
    //                map.put("Primary", "YES".equals(rs.getString("Key"))?"TRUE":"FALSE");
    //                map.put("Mandatory", !"YES".equals(rs.getString("Null"))?"TRUE":"FALSE");
    //                columns.add(map);
    //            }
    //
    //        }
    //        catch (SQLException e){
    //            e.printStackTrace();
    //        }finally{
    //            con.close();
    //        }
    //    // 输出
    //    for(HashMap<String,String> map : columns){
    //        String Name = map.get("Name");
    //        String Code = map.get("Code");
    //        String Comment = map.get("Comment");
    //        String DataType = map.get("DataType");
    //        String Primary = map.get("Primary");
    //        Name = Comment.split("\\s+")[0];
    //        String Mandatory = map.get("Mandatory");
    //        String sequence = map.get("sequence");
    //        String str = "table.cols.add(new Column(\""+Name+"\",\""+Code+"\",\""+Comment+"\",\""+DataType+"\",\""+Primary+"\",\""+Mandatory+"\",\""+(sequence==null?"":sequence)+"\"));";
    //        System.out.println(str);
    //    }
    //
    public void tableToBean(Connection connection, String tableName) throws Exception {
        List<HashMap<String, String>> dataList = printOracle(connection, tableName);
        System.out.println("before ##tComments=" + tableName);
        String tComments = getTableComments(tableName, connection);
        String className = getClassName(tableName);
        String[] strArrays = createInfo(dataList);
        System.out.println(className + "##tComments=" + tComments);
        //String sql = "select * from " + tableName + " where 1 <> 1";
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		ps = connection.prepareStatement(sql);
//		rs = ps.executeQuery();
//
//		ResultSetMetaData md = rs.getMetaData();
//		int columnCount = md.getColumnCount();
        StringBuffer sb = new StringBuffer();
//		String[] strArray=tableName.split("_");
//		tableName = tableName.substring(0, 1).toUpperCase() + tableName.subSequence(1, tableName.length());
//		tableName = this.dealLine(tableName);

        sb.append(createFileCommonts(className + "(" + tComments + ")"));
        sb.append(LINE);
        sb.append(LINE);
        sb.append("package " + this.packages + " ;");
        sb.append(LINE);

        //importPackage(md, columnCount, sb);
        sb.append(strArrays[0]).append(LINE);
        sb.append(LINE);

        sb.append(createClassCommonts(tComments + "(" + className + ")"));
        sb.append("public class " + className + " {");
        sb.append(LINE);
        //定义注释
        sb.append(strArrays[1]).append(LINE);

        sb.append(strArrays[2]).append(LINE);
        //defProperty(md, columnCount, sb);
        // genSetGet(md, columnCount, sb);
        sb.append("}");
        // String paths = System.getProperty("user.dir");

        //   System.out.println(sb+"##paths=="+paths);
        //  String endPath = paths + "\\src\\" + (packages.replace("/", "\\")).replace(".", "\\");
        buildJavaFile(OUT_PATH + "\\" + className + ".java", sb.toString());

    }

    public String createFileCommonts(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("/******************************************************************").append(LINE);
        sb.append("** 标    题：" + title).append(LINE);
        sb.append("** 创 建 者：").append(AUTHOR).append(LINE);
        sb.append("**创建日期：").append(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date())).append(LINE);
        sb.append("******************************************************************/").append(LINE);
        return sb.toString();
    }

    public String createClassCommonts(String title) {
        StringBuilder sb = new StringBuilder();
        sb.append("/**").append(LINE);
        sb.append(" *").append(title).append(LINE);
        sb.append(" *@author ").append(AUTHOR).append(LINE);
        sb.append(" * @version ").append(VERSION).append(" ").append(new SimpleDateFormat("yyyy-MM-dd").format(new Date())).append(LINE);
        sb.append(" */").append(LINE);

        return sb.toString();
    }

    public String[] createInfo(List<HashMap<String, String>> dataList) throws Exception {
        String[] strArray = new String[3];
        //1、生成导入包 2、生成注释和字段 3、生成get、set
        StringBuilder sb = new StringBuilder(), sb1 = new StringBuilder(), sb2 = new StringBuilder();
        HashSet<String> importSet = new HashSet<String>();
        for (HashMap<String, String> tmpMap : dataList) {
            //1、生成import
            importSet.add(tmpMap.get("dataType") + "_IMPORT");
            //2、生成注释和字段
            sb1.append(createFieldAndComment(tmpMap));
            //3、生成get、set
            sb2.append(genSetGet(tmpMap));
        }
        String importInfo = creatImportInfo(importSet);
        strArray[0] = importInfo + sb.toString();
        strArray[1] = sb1.toString();
        strArray[2] = sb2.toString();
        return strArray;
    }

    //处理import
    public String creatImportInfo(HashSet<String> tmpSet) {
        StringBuilder sb = new StringBuilder();
        for (String str : tmpSet) {
            String im = getImport(str);
            if (im != null) {
                sb.append(im + ";");
                sb.append(LINE);
            }
        }
        return sb.toString();
    }

    public String createFieldAndComment(HashMap<String, String> tmpMap) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append(TAB);
        String columnName = dealName(tmpMap.get("code"));
        //System.out.println("##columnName="+columnName+"##"+md.getColumnTypeName(i)+"##"+columnName.toLowerCase() );
        sb1.append("/**" + tmpMap.get("comments") + " */");
        sb1.append(LINE);
        sb1.append(TAB);
        sb1.append("private " + tmpMap.get("dataType") + " " + columnName + ";");
        sb1.append(LINE);
        return sb1.toString();
    }

    //属性生成get、 set 方法
    private String genSetGet(HashMap<String, String> tmpMap) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append(TAB);
        String pojoType = tmpMap.get("dataType");
        String columnName = dealName(tmpMap.get("code"));
        String getName = null;
        String setName = null;
        String getcomments = genGetSetCommets(tmpMap, 0);
        String setcomments = genGetSetCommets(tmpMap, 1);

        if (columnName.length() > 1) {
            getName = "public " + pojoType + " get" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1, columnName.length()) + "() {";
            setName = "public void set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1, columnName.length()) + "(" + pojoType + " " + columnName + ") {";
        } else {
            getName = "public get" + columnName + "() {";
            setName = "public set" + columnName + "(" + pojoType + " " + columnName.toLowerCase() + ") {";
        }
        sb.append(LINE).append(getcomments).append(LINE).append(TAB).append(getName);
        sb.append(LINE).append(TAB).append(TAB);
        sb.append("return " + columnName + ";");
        sb.append(LINE).append(TAB).append("}");
        sb.append(LINE);
        sb.append(LINE).append(setcomments).append(LINE).append(TAB).append(setName);
        sb.append(LINE).append(TAB).append(TAB);
        sb.append("this." + columnName + " = " + columnName + ";");
        sb.append(LINE).append(TAB).append("}");
        sb.append(LINE);
        return sb.toString();
    }

    public String genGetSetCommets(HashMap<String, String> tmpMap, Integer flag) {
        StringBuilder sb = new StringBuilder();
        sb.append(" /**").append(LINE);
        sb.append(TAB).append("*").append(tmpMap.get("comments")).append(LINE);
        sb.append(TAB).append("*").append(LINE);
        //get注释
        if (flag == 0) {
            sb.append(TAB).append("*@return ").append(tmpMap.get("comments")).append(LINE);
        } else { //set注释
            sb.append(TAB).append("*@param ").append(dealName(tmpMap.get("code"))).append(LINE);
            sb.append(TAB).append("*").append(TAB).append(TAB).append(TAB).append(TAB).append(TAB).append(tmpMap.get("comments")).append(LINE);
        }
        sb.append(TAB).append("*/");
        return sb.toString();

    }

    public List<HashMap<String, String>> printOracle(Connection con, String table) {
        List<HashMap<String, String>> columns = new ArrayList<HashMap<String, String>>();
        try {
            Statement stmt = con.createStatement();
            /*暂时不使用复杂语句
             * String sql=
          "select "+
          "         comments as \"Name\","+
          "         a.column_name \"Code\","+
          "         a.DATA_TYPE as \"DataType\","+
          "         b.comments as \"Comment\","+
          "         decode(c.column_name,null,'FALSE','TRUE') as \"Primary\","+
          "         decode(a.NULLABLE,'N','TRUE','Y','FALSE','') as \"Mandatory\","+
          "         '' \"sequence\""+
          "   from "+
          "       all_tab_columns a, "+
          "       all_col_comments b,"+
          "       ("+
          "        select a.constraint_name, a.column_name"+
          "          from user_cons_columns a, user_constraints b"+
          "         where a.constraint_name = b.constraint_name"+
          "               and b.constraint_type = 'P'"+
          "               and a.table_name = '"+table+"'"+
          "       ) c"+
          "   where "+
          "     a.Table_Name=b.table_Name "+
          "     and a.column_name=b.column_name"+
          "     and a.Table_Name='"+table+"'"+
          "     and a.owner=b.owner "+
          "     and a.owner='"+DB_OWNER.toUpperCase()+"'"+
          "     and a.COLUMN_NAME = c.column_name(+)" +
          "  order by a.COLUMN_ID";*/

            String sql = "select  a.column_name  as code  ,a.data_type as  dataType, b.comments as  comments,a.data_scale as scale, a.data_precision as length from  all_tab_columns a,  all_col_comments b  where a.Table_Name=b.table_Name and  " + " a.column_name=b.column_name and a.owner=b.owner and   a.Table_Name='" + table + "'  and a.owner='" + DB_OWNER.toUpperCase() + "' order by a.COLUMN_ID";
            System.out.println("sql==" + sql);
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("code", rs.getString("code"));
                map.put("comments", rs.getString("comments"));
                map.put("dataType", getConvertToJavaType(rs.getString("dataType"), rs.getString("scale"), rs.getString("length")));
                columns.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //         try {
            //			con.close();
            //		} catch (SQLException e) {
            //			// TODO Auto-generated catch block
            //			e.printStackTrace();
            //		}
        }
        return columns;

    }

    public String getTableComments(String tableName, Connection con) throws Exception {
        Statement stmt = con.createStatement();
        String sql = "select   a.comments as comments  from  user_tab_comments a  where  a.Table_Name='" + tableName + "' ";
        ResultSet rs = stmt.executeQuery(sql);
        if (rs.next()) {
            return rs.getString("comments");
        }
        return "";
    }

    public String getConvertToJavaType(String dataType, String scale, String length) {

        String retType = map.get(dataType);

        if (scale != null) {
            if (dataType.equalsIgnoreCase("NUMBER")) {
                System.out.println("###" + dataType + "(" + length + (Integer.parseInt(scale) > 0 ? "," + scale + "" : "") + ")");
                retType = map.get(dataType + "(" + length + (Integer.parseInt(scale) > 0 ? "," + scale + "" : "") + ")");
                retType = (retType == null) ? "Integer" : retType;
            }
        }
        // TODO Auto-generated method stub
        return retType;
    }

    //导入属性所需包
    private void importPackage(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {
        for (int i = 1; i <= columnCount; i++) {
            String im = getImport(md.getColumnTypeName(i) + "_IMPORT");
            if (im != null) {
                sb.append(im + ";");
                sb.append(LINE);
            }
        }
    }

    //属性定义
    private void defProperty(ResultSetMetaData md, int columnCount, StringBuffer sb) throws SQLException {

        for (int i = 1; i <= columnCount; i++) {
            sb.append(TAB);
            String columnName = dealLine(md, i);
            sb.append("private " + getPojoType(md.getColumnTypeName(i)) + " " + columnName.toLowerCase() + ";");
            sb.append(LINE);
        }
    }

    private String dealLine(ResultSetMetaData md, int i) throws SQLException {
        String columnName = md.getColumnName(i);
        //System.out.println("##ms=="+md.getCatalogName(i)+"##"+md.getColumnClassName(i)+"##"+md.getColumnType(i)+"##"+md.getColumnTypeName(i)+"##"+md.getPrecision(i));
        // 处理下划线情况，把下划线后一位的字母变大写；
        columnName = dealName(columnName);
        return columnName;
    }

    private String dealLine(String tableName) {
        // 处理下划线情况，把下划线后一位的字母变大写；
        tableName = dealName(tableName);
        return tableName;
    }

    //下划线后一位字母大写
    private String dealName(String columnName) {
        columnName = columnName.toLowerCase();
        if (columnName.contains("_")) {
            StringBuffer names = new StringBuffer();
            String arrayName[] = columnName.split("_");
            names.append(arrayName[0]);
            for (int i = 1; i < arrayName.length; i++) {
                String arri = arrayName[i];
                String tmp = arri.substring(0, 1).toUpperCase() + arri.substring(1, arri.length());
                names.append(tmp);
            }
            columnName = names.toString();
        }
        return columnName;
    }

    //下划线后一位字母大写
    public String getClassName(String tableName) {
        String className = "";
        for (int i = 0; i < DEL_PREFIX_TABLE.length; i++) {
            tableName = tableName.replaceAll(DEL_PREFIX_TABLE[i], "");
        }
        className = tableName.substring(0, 1).toUpperCase() + tableName.substring(1, tableName.length()).toLowerCase();
        System.out.println("className=" + className);
        if (className.contains("_")) {
            String[] classNames = className.split("_");
            String tmpClassName = "";
            for (int i = 0; i < classNames.length; i++) {
                tmpClassName = tmpClassName + classNames[i].substring(0, 1).toUpperCase() + classNames[i].substring(1, classNames[i].length());
            }

            className = tmpClassName;
        }

        return className + SUB_CLASS_FIX;
    }

    //生成java文件
    public void buildJavaFile(String filePath, String fileContent) {
        try {
            File file = new File(filePath);
            FileOutputStream osw = new FileOutputStream(file);
            PrintWriter pw = new PrintWriter(osw);
            pw.println(fileContent);
            pw.close();
        } catch (Exception e) {
            System.out.println("生成txt文件出错：" + e.getMessage());
        }
    }
}