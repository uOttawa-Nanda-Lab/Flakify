package com.github.jknack.handlebars;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HelpersTest {
@Test public void run() throws HandlebarsException, IOException {
  if (!skip(spec)) {
    run(alter(spec));
  }
 else {
    Report report=new Report();
    report.header(80);
    report.append("Skipping Test: %s",spec.id());
    report.header(80);
    throw new SkipTestException(spec.name());
  }
}

}