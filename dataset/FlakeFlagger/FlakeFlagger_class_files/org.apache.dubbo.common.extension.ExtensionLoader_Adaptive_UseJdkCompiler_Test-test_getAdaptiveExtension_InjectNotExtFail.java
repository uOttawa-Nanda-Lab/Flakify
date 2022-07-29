package org.apache.dubbo.common.extension;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ExtensionLoader_Adaptive_UseJdkCompiler_Test {
@Test public void test_getAdaptiveExtension_InjectNotExtFail() throws Exception {
  Ext6 ext=ExtensionLoader.getExtensionLoader(Ext6.class).getExtension("impl2");
  Ext6Impl2 impl=(Ext6Impl2)ext;
  assertNull(impl.getList());
}

}