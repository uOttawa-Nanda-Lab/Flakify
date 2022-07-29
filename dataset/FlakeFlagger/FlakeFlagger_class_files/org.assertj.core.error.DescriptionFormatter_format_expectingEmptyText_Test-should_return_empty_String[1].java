package org.assertj.core.error;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DescriptionFormatter_format_expectingEmptyText_Test {
@Test public void should_return_empty_String(){
  assertEquals("",formatter.format(description));
}

}