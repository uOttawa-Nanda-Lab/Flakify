package okio;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class RealBufferedSourceReadUtf8LineTest {
@Test public void bufferedReaderCompatible() throws IOException {
  BufferedSource source=newSource("abc\ndef");
  assertEquals("abc",source.readUtf8Line(false));
  assertEquals("def",source.readUtf8Line(false));
  assertEquals(null,source.readUtf8Line(false));
}

}