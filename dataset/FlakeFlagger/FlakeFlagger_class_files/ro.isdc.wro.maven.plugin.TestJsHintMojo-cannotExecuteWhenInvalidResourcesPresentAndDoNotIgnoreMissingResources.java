package ro.isdc.wro.maven.plugin;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TestJsHintMojo {
@Test(expected=MojoExecutionException.class) public void cannotExecuteWhenInvalidResourcesPresentAndDoNotIgnoreMissingResources() throws Exception {
  setWroWithInvalidResources();
  mojo.setIgnoreMissingResources(false);
  mojo.execute();
}

}