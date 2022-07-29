package org.apache.dubbo.common.extension;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ExtensionLoader_Adaptive_UseJdkCompiler_Test {
@Test public void test_getAdaptiveExtension_ExceptionWhenNoUrlAttribute() throws Exception {
  try {
    ExtensionLoader.getExtensionLoader(NoUrlParamExt.class).getAdaptiveExtension();
    fail();
  }
 catch (  Exception expected) {
    assertThat(expected.getMessage(),containsString("fail to create adaptive class for interface "));
    assertThat(expected.getMessage(),containsString(": not found url parameter or url attribute in parameters of method "));
  }
}

}