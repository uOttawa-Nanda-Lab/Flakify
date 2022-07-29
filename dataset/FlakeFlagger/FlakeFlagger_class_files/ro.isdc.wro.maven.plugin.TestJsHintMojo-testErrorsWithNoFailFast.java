package ro.isdc.wro.maven.plugin;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TestJsHintMojo {
@Test public void testErrorsWithNoFailFast() throws Exception {
  mojo.setFailNever(true);
  mojo.setOptions("undef, browser");
  mojo.setTargetGroups("undef");
  mojo.execute();
}

}