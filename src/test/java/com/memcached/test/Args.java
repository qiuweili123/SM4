package com.memcached.test;

import org.kohsuke.args4j.Option;

public class Args {
    @Option(required = true, name = "-concurrent", usage = "concurrent is something")
    private int concurrent;

    @Option(name = "-loopCount", usage = "loopCount is loopCount")
    private int loopCount;

    /**
     * @return the concurrent
     */
    public int getConcurrent() {
        return this.concurrent;
    }

    /**
     * @param concurrent the concurrent to set
     */
    public void setConcurrent(int concurrent) {
        this.concurrent = concurrent;
    }

    /**
     * @return the loopCount
     */
    public int getLoopCount() {
        return this.loopCount;
    }

    /**
     * @param loopCount the loopCount to set
     */
    public void setLoopCount(int loopCount) {
        this.loopCount = loopCount;
    }


}