package org.apache.dubbo.common.extension;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ExtensionLoader_Adaptive_UseJdkCompiler_Test {
@Test public void test_urlHolder_getAdaptiveExtension_UrlNpe() throws Exception {
  Ext2 ext=ExtensionLoader.getExtensionLoader(Ext2.class).getAdaptiveExtension();
  try {
    ext.echo(null,"haha");
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("org.apache.dubbo.common.extension.ext2.UrlHolder argument == null",e.getMessage());
  }
  try {
    ext.echo(new UrlHolder(),"haha");
    fail();
  }
 catch (  IllegalArgumentException e) {
    assertEquals("org.apache.dubbo.common.extension.ext2.UrlHolder argument getUrl() == null",e.getMessage());
  }
}

}