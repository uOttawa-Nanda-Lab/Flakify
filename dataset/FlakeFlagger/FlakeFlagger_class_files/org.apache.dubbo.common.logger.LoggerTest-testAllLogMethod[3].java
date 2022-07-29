package org.apache.dubbo.common.logger;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class LoggerTest {
@Test public void testAllLogMethod(){
  logger.error("error");
  logger.warn("warn");
  logger.info("info");
  logger.debug("debug");
  logger.trace("info");
  logger.error(new Exception("error"));
  logger.warn(new Exception("warn"));
  logger.info(new Exception("info"));
  logger.debug(new Exception("debug"));
  logger.trace(new Exception("trace"));
  logger.error("error",new Exception("error"));
  logger.warn("warn",new Exception("warn"));
  logger.info("info",new Exception("info"));
  logger.debug("debug",new Exception("debug"));
  logger.trace("trace",new Exception("trace"));
}

}