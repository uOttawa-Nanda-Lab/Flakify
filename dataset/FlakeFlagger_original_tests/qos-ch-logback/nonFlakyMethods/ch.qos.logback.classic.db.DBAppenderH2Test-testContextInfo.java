@Test public void testContextInfo() throws SQLException {
  loggerContext.putProperty("testKey1","testValue1");
  MDC.put("k" + diff,"v" + diff);
  ILoggingEvent event=createLoggingEvent();
  appender.append(event);
  Statement stmt=connectionSource.getConnection().createStatement();
  ResultSet rs=null;
  rs=stmt.executeQuery("SELECT * FROM LOGGING_EVENT_PROPERTY WHERE EVENT_ID=1");
  Map<String,String> map=appender.mergePropertyMaps(event);
  int i=0;
  while (rs.next()) {
    String key=rs.getString(2);
    assertEquals(map.get(key),rs.getString(3));
    i++;
  }
  assertTrue(map.size() != 0);
  assertEquals(map.size(),i);
  rs.close();
  stmt.close();
}
