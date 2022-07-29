package org.apache.dubbo.common.logger;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class LoggerAdapterTest {
@Test public void testLevel(){
  for (  Level targetLevel : Level.values()) {
    loggerAdapter.setLevel(targetLevel);
    assertThat(loggerAdapter.getLevel(),is(targetLevel));
  }
}

}