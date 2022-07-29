package org.apache.dubbo.common.extension;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ExtensionLoader_Adaptive_UseJdkCompiler_Test {
@Test public void test_useAdaptiveClass() throws Exception {
  ExtensionLoader<HasAdaptiveExt> loader=ExtensionLoader.getExtensionLoader(HasAdaptiveExt.class);
  HasAdaptiveExt ext=loader.getAdaptiveExtension();
  assertTrue(ext instanceof HasAdaptiveExt_ManualAdaptive);
}

}