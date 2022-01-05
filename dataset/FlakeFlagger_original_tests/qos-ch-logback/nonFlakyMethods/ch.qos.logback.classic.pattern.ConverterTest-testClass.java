@Test public void testClass(){
  DynamicConverter<ILoggingEvent> converter=new ClassOfCallerConverter();
  StringBuilder buf=new StringBuilder();
  converter.write(buf,le);
  assertEquals(this.getClass().getName(),buf.toString());
}
