package ro.isdc.wro.maven.plugin;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TestJsLintMojo {
@Test(expected=CustomException.class) public void shouldOverrideCustomProcessorsFactory() throws Throwable {
  try {
    mojo.setWroManagerFactory(CustomWroManagerFactory.class.getName());
    mojo.setTargetGroups(null);
    mojo.execute();
  }
 catch (  final MojoExecutionException e) {
    throw e.getCause();
  }
}

}