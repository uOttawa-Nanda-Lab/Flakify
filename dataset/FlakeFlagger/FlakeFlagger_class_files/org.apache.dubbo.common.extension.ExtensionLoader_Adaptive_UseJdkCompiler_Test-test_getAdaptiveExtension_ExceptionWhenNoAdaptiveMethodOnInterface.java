package org.apache.dubbo.common.extension;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ExtensionLoader_Adaptive_UseJdkCompiler_Test {
@Test public void test_getAdaptiveExtension_ExceptionWhenNoAdaptiveMethodOnInterface() throws Exception {
  try {
    ExtensionLoader.getExtensionLoader(NoAdaptiveMethodExt.class).getAdaptiveExtension();
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),allOf(containsString("Can not create adaptive extension interface org.apache.dubbo.common.extension.ext5.NoAdaptiveMethodExt"),containsString("No adaptive method on extension org.apache.dubbo.common.extension.ext5.NoAdaptiveMethodExt, refuse to create the adaptive class")));
  }
  try {
    ExtensionLoader.getExtensionLoader(NoAdaptiveMethodExt.class).getAdaptiveExtension();
    fail();
  }
 catch (  IllegalStateException expected) {
    assertThat(expected.getMessage(),allOf(containsString("Can not create adaptive extension interface org.apache.dubbo.common.extension.ext5.NoAdaptiveMethodExt"),containsString("No adaptive method on extension org.apache.dubbo.common.extension.ext5.NoAdaptiveMethodExt, refuse to create the adaptive class")));
  }
}

}