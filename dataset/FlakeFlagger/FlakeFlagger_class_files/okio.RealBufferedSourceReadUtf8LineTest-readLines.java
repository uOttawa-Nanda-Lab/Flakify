package okio;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class RealBufferedSourceReadUtf8LineTest {
@Test public void readLines() throws IOException {
  BufferedSource source=newSource("abc\ndef\n");
  assertEquals("abc",source.readUtf8Line(true));
  assertEquals("def",source.readUtf8Line(true));
  try {
    source.readUtf8Line(true);
    fail();
  }
 catch (  EOFException expected) {
  }
}

}