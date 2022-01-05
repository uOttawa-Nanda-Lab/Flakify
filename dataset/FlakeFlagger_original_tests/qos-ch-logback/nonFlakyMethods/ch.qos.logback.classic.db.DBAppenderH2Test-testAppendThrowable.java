@Test public void testAppendThrowable() throws SQLException {
  ILoggingEvent event=createLoggingEvent();
  appender.append(event);
  Statement stmt=connectionSource.getConnection().createStatement();
  ResultSet rs=null;
  rs=stmt.executeQuery("SELECT * FROM LOGGING_EVENT_EXCEPTION WHERE EVENT_ID=1");
  rs.next();
  String expected="java.lang.Exception: test Ex";
  String firstLine=rs.getString(3);
  assertTrue("[" + firstLine + "] does not match ["+ expected+ "]",firstLine.contains(expected));
  int i=0;
  while (rs.next()) {
    expected=event.getThrowableProxy().getStackTraceElementProxyArray()[i].toString();
    String st=rs.getString(3);
    assertTrue("[" + st + "] does not match ["+ expected+ "]",st.contains(expected));
    i++;
  }
  assertTrue(i != 0);
  rs.close();
  stmt.close();
}
