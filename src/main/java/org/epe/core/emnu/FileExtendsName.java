package org.epe.core.emnu;

/**
 * �ļ���չ��,֧��xls,xlsx����office2003��office2007
 *
 * @author ������
 */
public enum FileExtendsName {

    xls("xls"), xlsx("xlsx");

    private String fileExtendsName;

    private FileExtendsName(String fileExtendsName) {
        this.fileExtendsName = fileExtendsName;
    }

    public String getFileExtendsName() {
        return fileExtendsName;
    }
}
