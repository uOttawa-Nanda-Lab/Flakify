@Test public void testRelativeTime() throws Exception {
  DynamicConverter<ILoggingEvent> converter=new RelativeTimeConverter();
  StringBuilder buf0=new StringBuilder();
  StringBuilder buf1=new StringBuilder();
  ILoggingEvent e0=makeLoggingEvent(null);
  ILoggingEvent e1=makeLoggingEvent(null);
  converter.write(buf0,e0);
  converter.write(buf1,e1);
  assertEquals(buf0.toString(),buf1.toString());
}
