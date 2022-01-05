@Test public void testLogger(){
{
    ClassicConverter converter=new LoggerConverter();
    StringBuilder buf=new StringBuilder();
    converter.write(buf,le);
    assertEquals(this.getClass().getName(),buf.toString());
  }
{
    ClassicConverter converter=new LoggerConverter();
    this.optionList.add("20");
    converter.setOptionList(this.optionList);
    converter.start();
    StringBuilder buf=new StringBuilder();
    converter.write(buf,le);
    assertEquals("c.q.l.c.p.ConverterTest",buf.toString());
  }
{
    DynamicConverter<ILoggingEvent> converter=new LoggerConverter();
    this.optionList.clear();
    this.optionList.add("0");
    converter.setOptionList(this.optionList);
    converter.start();
    StringBuilder buf=new StringBuilder();
    converter.write(buf,le);
    assertEquals("ConverterTest",buf.toString());
  }
}
