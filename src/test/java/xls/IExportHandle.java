package xls;

import org.springframework.util.ClassUtils;

import java.io.IOException;

public interface IExportHandle<T> {

    ClassLoader DEFAULT_CLASS_LOADER = ClassUtils.getDefaultClassLoader();

    void export(ExportSetting exportSeting, T file) throws IOException;

    enum ExportType {

        XLSX("xlsx", "application/excel"), XLS("xlsx", "application/excel"), TXT("txt", "text/plain");

        private String fileType;
        private String contentType;

        ExportType(String fileType, String contentType) {
            this.fileType = fileType;
            this.contentType = contentType;
        }

        public String getFileType() {
            return fileType;
        }

        public String getContentType() {
            return contentType;
        }
    }
}
