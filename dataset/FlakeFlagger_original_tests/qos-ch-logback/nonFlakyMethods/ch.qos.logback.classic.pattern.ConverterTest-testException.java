@Test public void testException(){
{
    DynamicConverter<ILoggingEvent> converter=new ThrowableProxyConverter();
    StringBuilder buf=new StringBuilder();
    converter.write(buf,le);
  }
{
    DynamicConverter<ILoggingEvent> converter=new ThrowableProxyConverter();
    this.optionList.add("3");
    converter.setOptionList(this.optionList);
    StringBuilder buf=new StringBuilder();
    converter.write(buf,le);
  }
}
