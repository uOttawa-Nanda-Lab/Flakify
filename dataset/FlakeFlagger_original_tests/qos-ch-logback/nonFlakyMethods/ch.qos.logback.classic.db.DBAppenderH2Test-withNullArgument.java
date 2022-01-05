@Test public void withNullArgument() throws SQLException {
  ILoggingEvent event=createLoggingEvent("Processing code {}; code type is {}; request date {}; record from date {}",new Object[]{1,2,new Date(),null});
  appender.append(event);
  Statement stmt=connectionSource.getConnection().createStatement();
  ResultSet rs=null;
  rs=stmt.executeQuery("SELECT * FROM logging_event");
  if (rs.next()) {
    assertEquals(event.getTimeStamp(),rs.getLong(DBAppender.TIMESTMP_INDEX));
    assertEquals(event.getFormattedMessage(),rs.getString(DBAppender.FORMATTED_MESSAGE_INDEX));
  }
}
