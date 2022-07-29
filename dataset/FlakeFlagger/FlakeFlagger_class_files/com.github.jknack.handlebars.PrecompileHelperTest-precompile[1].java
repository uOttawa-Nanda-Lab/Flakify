package com.github.jknack.handlebars;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class PrecompileHelperTest {
@Test public void precompile() throws IOException {
  String js=handlebars.compile("{{precompile \"input\" wrapper=\"" + wrapper + "\"}}").apply("Handlebar.js");
  InputStream in=getClass().getResourceAsStream("/" + wrapper + ".precompiled.js");
  assertEquals(IOUtils.toString(in),js);
  in.close();
}

}