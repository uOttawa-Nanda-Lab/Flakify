@Test public void testAppendLoggingEvent() throws SQLException {
  ILoggingEvent event=createLoggingEvent();
  appender.append(event);
  StatusPrinter.printInCaseOfErrorsOrWarnings(lc);
  ResultSet rs=null;
  rs=stmt.executeQuery("SELECT * FROM logging_event where EVENT_ID = " + existingRowCount);
  if (rs.next()) {
    assertEquals(event.getTimeStamp(),rs.getLong(DBAppender.TIMESTMP_INDEX));
    assertEquals(event.getFormattedMessage(),rs.getString(DBAppender.FORMATTED_MESSAGE_INDEX));
    assertEquals(event.getLoggerName(),rs.getString(DBAppender.LOGGER_NAME_INDEX));
    assertEquals(event.getLevel().toString(),rs.getString(DBAppender.LEVEL_STRING_INDEX));
    assertEquals(event.getThreadName(),rs.getString(DBAppender.THREAD_NAME_INDEX));
    assertEquals(DBHelper.computeReferenceMask(event),rs.getShort(DBAppender.REFERENCE_FLAG_INDEX));
    assertEquals(String.valueOf(diff),rs.getString(DBAppender.ARG0_INDEX));
    StackTraceElement callerData=event.getCallerData()[0];
    assertEquals(callerData.getFileName(),rs.getString(DBAppender.CALLER_FILENAME_INDEX));
    assertEquals(callerData.getClassName(),rs.getString(DBAppender.CALLER_CLASS_INDEX));
    assertEquals(callerData.getMethodName(),rs.getString(DBAppender.CALLER_METHOD_INDEX));
  }
 else {
    fail("No row was inserted in the database");
  }
  rs.close();
}
