package org.apache.dubbo.common.extension;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ExtensionLoader_Adaptive_UseJdkCompiler_Test {
@Test public void test_getAdaptiveExtension_defaultAdaptiveKey() throws Exception {
{
    SimpleExt ext=ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
    Map<String,String> map=new HashMap<String,String>();
    URL url=new URL("p1","1.2.3.4",1010,"path1",map);
    String echo=ext.echo(url,"haha");
    assertEquals("Ext1Impl1-echo",echo);
  }
{
    SimpleExt ext=ExtensionLoader.getExtensionLoader(SimpleExt.class).getAdaptiveExtension();
    Map<String,String> map=new HashMap<String,String>();
    map.put("simple.ext","impl2");
    URL url=new URL("p1","1.2.3.4",1010,"path1",map);
    String echo=ext.echo(url,"haha");
    assertEquals("Ext1Impl2-echo",echo);
  }
}

}