package org.apache.dubbo.common.extension;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ExtensionLoader_Adaptive_UseJdkCompiler_Test {
@Test public void test_getAdaptiveExtension_UrlNpe() throws Exception {
  SimpleExt ext=ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
  try {
    ext.echo(null,"haha");
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("url == null",e.getMessage());
  }
}

}