@Test public void testAppendMultipleEvents() throws SQLException {
  for (int i=0; i < 10; i++) {
    ILoggingEvent event=createLoggingEvent();
    appender.append(event);
  }
  Statement stmt=connectionSource.getConnection().createStatement();
  ResultSet rs=null;
  rs=stmt.executeQuery("SELECT * FROM logging_event");
  int count=0;
  while (rs.next()) {
    count++;
  }
  assertEquals(10,count);
  rs.close();
  stmt.close();
}
