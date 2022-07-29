package org.apache.dubbo.common.logger;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class LoggerAdapterTest {
@Test public void testGetLogger(){
  Logger logger=loggerAdapter.getLogger(this.getClass());
  assertThat(logger.getClass().isAssignableFrom(this.loggerClass),is(true));
  logger=loggerAdapter.getLogger(this.getClass().getSimpleName());
  assertThat(logger.getClass().isAssignableFrom(this.loggerClass),is(true));
}

}