package org.apache.dubbo.remoting.transport.codec;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class CodecAdapterTest {
@Test(expected=IOException.class) public void testDecode_Backspace_WithError() throws IOException {
  url=url.addParameter(AbstractMockChannel.ERROR_WHEN_SEND,Boolean.TRUE.toString());
  testDecode_Backspace();
  url=url.removeParameter(AbstractMockChannel.ERROR_WHEN_SEND);
}

}