package com.anhoo.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;

public class BaseBean implements Serializable {

    private static final long serialVersionUID = 6500269667754887332L;

    protected static Logger logger = LogManager.getLogger();

    public void debug(String debugInfo) {
        logger.debug(this.getClass() + ":debug=> " + debugInfo);
    }

    public void info(String information) {
        logger.info(this.getClass() + ":info=> " + information);
    }

    public void error(String errorInfo) {
        logger.error(this.getClass() + ":error=> " + errorInfo);
    }

    public void error(String errorInfo, Throwable throwable) {
        logger.error(this.getClass() + ":error=> " + errorInfo, throwable);
    }

}
