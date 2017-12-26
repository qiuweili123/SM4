package xls;

import java.util.HashMap;
import java.util.Map;

public class ExportSetting {
    /**
     * 导出文件名称：
     * 格式：文件名前缀.序号.文件类型后缀
     */

    private String prefixFileName;

    private String midFileName;

    private String fileType;

    private String contentType;
    /**
     * 模板名称
     */
    private String template;

    /**
     * 存储导出数据
     */
    private Map<String, Object> dataMap;

    /**
     * 存储返回结果数据
     */
    private Map<String, Object> resultMap;

    private IExportHandle.ExportType exportType;

    public ExportSetting(String prefixFileNname, String midFileName, String template) {
        this.prefixFileName = prefixFileNname;
        this.midFileName = midFileName;
        this.template = template;
        resultMap = new HashMap<>(2);
    }

    public String getTemplate() {
        return template;
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public ExportSetting setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
        return this;
    }

    public String getContentType() {
        return this.exportType.getContentType();
    }

    public Map<String, Object> getResultMap() {
        return resultMap;
    }

    public ExportSetting setExportType(IExportHandle.ExportType exportType) {
        this.exportType = exportType;
        return this;
    }

    public ExportSetting addRetValue(String key, Object object) {
        resultMap.put(key, object);
        return this;
    }

    public String getFullFileNameName() {
        return this.prefixFileName + "." + this.midFileName + "." + this.exportType.getFileType();
    }
}
