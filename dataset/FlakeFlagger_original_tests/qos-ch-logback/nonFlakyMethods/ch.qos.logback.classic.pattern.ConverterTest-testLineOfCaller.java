@Test public void testLineOfCaller(){
{
    DynamicConverter<ILoggingEvent> converter=new LineOfCallerConverter();
    StringBuilder buf=new StringBuilder();
    converter.write(buf,le);
    assertEquals("75",buf.toString());
  }
}
