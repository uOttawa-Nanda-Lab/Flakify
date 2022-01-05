@Test public void testAppendMultipleEvents() throws SQLException {
  int numEvents=3;
  for (int i=0; i < numEvents; i++) {
    ILoggingEvent event=createLoggingEvent();
    appender.append(event);
  }
  Statement stmt=connectionSource.getConnection().createStatement();
  ResultSet rs=null;
  rs=stmt.executeQuery("SELECT * FROM logging_event WHERE EVENT_ID >=" + existingRowCount);
  int count=0;
  while (rs.next()) {
    count++;
  }
  assertEquals(numEvents,count);
  rs.close();
}
