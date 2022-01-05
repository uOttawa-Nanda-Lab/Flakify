@Test public void testLineSeparator(){
  DynamicConverter<ILoggingEvent> converter=new LineSeparatorConverter();
  StringBuilder buf=new StringBuilder();
  converter.write(buf,le);
  assertEquals(CoreConstants.LINE_SEPARATOR,buf.toString());
}
