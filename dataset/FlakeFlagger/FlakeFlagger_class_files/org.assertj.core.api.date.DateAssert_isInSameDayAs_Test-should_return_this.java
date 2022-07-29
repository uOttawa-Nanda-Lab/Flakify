package org.assertj.core.api.date;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DateAssert_isInSameDayAs_Test {
@Test public void should_return_this(){
  DateAssert returned=assertionInvocationWithDateArg();
  assertSame(assertions,returned);
}

}