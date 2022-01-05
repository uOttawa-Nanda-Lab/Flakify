@Test public void testFileOfCaller(){
  DynamicConverter<ILoggingEvent> converter=new FileOfCallerConverter();
  StringBuilder buf=new StringBuilder();
  converter.write(buf,le);
  assertEquals("ConverterTest.java",buf.toString());
}
