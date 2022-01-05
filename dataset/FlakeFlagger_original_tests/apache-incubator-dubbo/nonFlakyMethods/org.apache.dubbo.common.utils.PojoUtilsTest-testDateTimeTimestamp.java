@Test public void testDateTimeTimestamp() throws Exception {
  String dateStr="2018-09-12";
  String timeStr="10:12:33";
  String dateTimeStr="2018-09-12 10:12:33";
  String[] dateFormat=new String[]{"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd","HH:mm:ss"};
  Object date=PojoUtils.realize(dateTimeStr,Date.class,(Type)Date.class);
  assertEquals(Date.class,date.getClass());
  assertEquals(dateTimeStr,new SimpleDateFormat(dateFormat[0]).format(date));
  Object time=PojoUtils.realize(dateTimeStr,java.sql.Time.class,(Type)java.sql.Time.class);
  assertEquals(java.sql.Time.class,time.getClass());
  assertEquals(timeStr,new SimpleDateFormat(dateFormat[2]).format(time));
  Object sqlDate=PojoUtils.realize(dateTimeStr,java.sql.Date.class,(Type)java.sql.Date.class);
  assertEquals(java.sql.Date.class,sqlDate.getClass());
  assertEquals(dateStr,new SimpleDateFormat(dateFormat[1]).format(sqlDate));
  Object timestamp=PojoUtils.realize(dateTimeStr,java.sql.Timestamp.class,(Type)java.sql.Timestamp.class);
  assertEquals(java.sql.Timestamp.class,timestamp.getClass());
  assertEquals(dateTimeStr,new SimpleDateFormat(dateFormat[0]).format(timestamp));
}
