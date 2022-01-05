@Test public void testContextInfo() throws SQLException {
  lc.putProperty("testKey1","testValue1");
  MDC.put("k" + diff,"v" + diff);
  ILoggingEvent event=createLoggingEvent();
  appender.append(event);
  Statement stmt=connectionSource.getConnection().createStatement();
  ResultSet rs=null;
  rs=stmt.executeQuery("SELECT * FROM LOGGING_EVENT_PROPERTY  WHERE EVENT_ID = " + existingRowCount);
  Map<String,String> map=appender.mergePropertyMaps(event);
  System.out.println("ma.size=" + map.size());
  int i=0;
  while (rs.next()) {
    String key=rs.getString(2);
    assertEquals(map.get(key),rs.getString(3));
    i++;
  }
  assertTrue(map.size() != 0);
  assertEquals(map.size(),i);
  rs.close();
}
