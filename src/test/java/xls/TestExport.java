package xls;

import com.google.common.collect.ImmutableMap;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

import java.io.*;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by lenovo on 2017/12/20.
 */
public class TestExport {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException, FileNotFoundException {
        String fileTemPlan = "";
        Map<Integer, String> StatusMap = ImmutableMap.of(1, "正常", 2, "冻结", 3, "退卡", 4, "已转卡");

        ExportSetting exportSeting = new ExportSetting("mamber_card", String.valueOf(System.currentTimeMillis()), "xls/member_card_template.xlsx");

        DynaProperty[] props = new DynaProperty[]{
                new DynaProperty("startDate", java.util.Date.class),
                new DynaProperty("id", Integer.class),
                new DynaProperty("status", Integer.class)
        };
        BasicDynaClass card = new BasicDynaClass("memberCard", null, props);

        List<DynaBean> dataList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            DynaBean dynaBean = card.newInstance();
            dynaBean.set("startDate", new Date());
            dynaBean.set("status", i);
            dynaBean.set("id", i);
            dataList.add(dynaBean);
        }

        Map<String, Object> data = new HashMap<>(4);
        data.put("dateFormat", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        data.put("memberCards", dataList);
        data.put("status", StatusMap);
        exportSeting.setDataMap(data);
        exportSeting.setExportType(IExportHandle.ExportType.XLSX);

        String filePath = ExportUtil.getFilePath(exportSeting);
        System.out.println("##path ==" + filePath);
        FileOutputStream outputStream = new FileOutputStream(filePath);
        IExportHandle<OutputStream> handle = (seting, os) -> {
            File file = new File(filePath);
            FileInputStream fileInputStream = new FileInputStream(file);
            FileChannel channel = fileInputStream.getChannel();
            System.out.println("文件大小为：" + channel.size());

            ZipArchiveOutputStream zaos = new ZipArchiveOutputStream(new File(filePath + ".zip"));

            //Use Zip64 extensions for all entries where they are required
            zaos.setUseZip64(Zip64Mode.AsNeeded);
            //将每个文件用ZipArchiveEntry封装
            //再用ZipArchiveOutputStream写到压缩文件中
            ZipArchiveEntry zipArchiveEntry = new ZipArchiveEntry(file, file.getName());
            zaos.putArchiveEntry(zipArchiveEntry);
            InputStream is = null;
            try {
                is = new BufferedInputStream(new FileInputStream(file));
                byte[] buffer = new byte[1024 * 5];
                int len = -1;
                while ((len = is.read(buffer)) != -1) {
                    //把缓冲区的字节写入到ZipArchiveEntry
                    zaos.write(buffer, 0, len);
                }
                //Writes all necessary data for this entry.
                zaos.closeArchiveEntry();
            } catch (Exception e) {
                throw new RuntimeException(e);
            } finally {
                if (is != null)
                    is.close();
            }
            zaos.finish();
        };

        ExportUtil.exportXls(exportSeting, handle, outputStream);

    }

}
