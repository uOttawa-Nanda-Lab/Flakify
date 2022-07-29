package org.assertj.core.internal.strings;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class Strings_assertEqualsIgnoringWhitespace_Successful_Test {
@Test public void should_pass_if_both_Strings_are_equal_ignoring_whitespace(){
  strings.assertEqualsIgnoringWhitespace(someInfo(),actual,expected);
}

}