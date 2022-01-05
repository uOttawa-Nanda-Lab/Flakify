@Test public void testMethodOfCaller(){
  DynamicConverter<ILoggingEvent> converter=new MethodOfCallerConverter();
  StringBuilder buf=new StringBuilder();
  converter.write(buf,le);
  assertEquals("testMethodOfCaller",buf.toString());
}
