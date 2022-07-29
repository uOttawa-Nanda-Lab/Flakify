package org.apache.dubbo.common.extension;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ExtensionLoader_Adaptive_UseJdkCompiler_Test {
@Test public void test_urlHolder_getAdaptiveExtension_noExtension() throws Exception {
  Ext2 ext=ExtensionLoader.getExtensionLoader(Ext2.class).getAdaptiveExtension();
  URL url=new URL("p1","1.2.3.4",1010,"path1");
  UrlHolder holder=new UrlHolder();
  holder.setUrl(url);
  try {
    ext.echo(holder,"haha");
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("Fail to get extension("));
  }
  url=url.addParameter("ext2","XXX");
  holder.setUrl(url);
  try {
    ext.echo(holder,"haha");
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),containsString("No such extension"));
  }
}

}