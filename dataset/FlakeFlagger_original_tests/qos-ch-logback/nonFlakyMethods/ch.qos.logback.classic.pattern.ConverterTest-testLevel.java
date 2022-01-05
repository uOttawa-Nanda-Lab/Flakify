@Test public void testLevel(){
{
    DynamicConverter<ILoggingEvent> converter=new LevelConverter();
    StringBuilder buf=new StringBuilder();
    converter.write(buf,le);
    assertEquals("INFO",buf.toString());
  }
{
    DynamicConverter<ILoggingEvent> converter=new LevelConverter();
    converter.setFormattingInfo(new FormatInfo(1,1,true,false));
    StringBuilder buf=new StringBuilder();
    converter.write(buf,le);
    assertEquals("I",buf.toString());
  }
}
