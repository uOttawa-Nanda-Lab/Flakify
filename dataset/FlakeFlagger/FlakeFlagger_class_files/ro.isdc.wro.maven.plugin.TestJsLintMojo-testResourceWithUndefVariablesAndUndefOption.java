package ro.isdc.wro.maven.plugin;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class TestJsLintMojo {
@Test(expected=MojoExecutionException.class) public void testResourceWithUndefVariablesAndUndefOption() throws Exception {
  mojo.setOptions("undef, browser");
  mojo.setTargetGroups("undef");
  mojo.execute();
}

}