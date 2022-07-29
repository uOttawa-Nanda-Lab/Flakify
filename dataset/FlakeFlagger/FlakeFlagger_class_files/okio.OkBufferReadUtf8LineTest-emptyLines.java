package okio;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class OkBufferReadUtf8LineTest {
@Test public void emptyLines() throws IOException {
  BufferedSource source=newSource("\n\n\n");
  assertEquals("",source.readUtf8Line(true));
  assertEquals("",source.readUtf8Line(true));
  assertEquals("",source.readUtf8Line(true));
  assertTrue(source.exhausted());
}

}