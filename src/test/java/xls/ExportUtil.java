package xls;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

@Component
public class ExportUtil {


    public static <T extends OutputStream> File createZipFile(ExportSetting exportSeting, T os) {


        return null;
    }

    public static String getFilePath(ExportSetting exportSeting) {

        String filePath = FileUtils.getUserDirectoryPath() + File.separator + exportSeting.getFullFileNameName();

        return filePath;
    }

    public static <T extends OutputStream> void exportXls(ExportSetting exportSeting, IExportHandle<T> exportHandle, T os) {

        checkExportSeting(exportSeting);

        InputStream is = IExportHandle.DEFAULT_CLASS_LOADER.getResourceAsStream(exportSeting.getTemplate());

        Context context = new Context(exportSeting.getDataMap());

        try {
            JxlsHelper.getInstance().processTemplate(is, os, context);
            exportHandle.export(exportSeting, os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(is);
            IOUtils.closeQuietly(os);
        }
    }

    private static void checkExportSeting(ExportSetting exportSeting) {
        if (StringUtils.isEmpty(exportSeting.getTemplate()) || MapUtils.isEmpty(exportSeting.getDataMap())) {
            return;
        }
    }

}
