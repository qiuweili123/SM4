package org.epe.core.emnu;


public enum FileDateFormat {

    yyyyMMddHHmmss("fileName");
    private String fileDateFormat;

    private FileDateFormat(String fileDateFormat) {
        this.fileDateFormat = fileDateFormat;
    }

    public String getFileDateFormat() {
        return fileDateFormat;
    }

}
