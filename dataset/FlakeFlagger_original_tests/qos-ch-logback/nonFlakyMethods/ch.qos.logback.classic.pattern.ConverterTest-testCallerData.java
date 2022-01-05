@Test public void testCallerData(){
{
    DynamicConverter<ILoggingEvent> converter=new CallerDataConverter();
    converter.start();
    StringBuilder buf=new StringBuilder();
    converter.write(buf,le);
    if (buf.length() < 10) {
      fail("buf is too short");
    }
  }
{
    DynamicConverter<ILoggingEvent> converter=new CallerDataConverter();
    this.optionList.add("2");
    this.optionList.add("XXX");
    converter.setOptionList(this.optionList);
    converter.start();
    StringBuilder buf=new StringBuilder();
    LoggingEvent event=makeLoggingEvent(null);
    event.setMarker(MarkerFactory.getMarker("XXX"));
    converter.write(buf,event);
    if (buf.length() < 10) {
      fail("buf is too short");
    }
  }
{
    DynamicConverter<ILoggingEvent> converter=new CallerDataConverter();
    this.optionList.clear();
    this.optionList.add("2");
    this.optionList.add("XXX");
    this.optionList.add("*");
    converter.setOptionList(this.optionList);
    converter.start();
    StringBuilder buf=new StringBuilder();
    LoggingEvent event=makeLoggingEvent(null);
    event.setMarker(MarkerFactory.getMarker("YYY"));
    converter.write(buf,event);
    if (buf.length() < 10) {
      fail("buf is too short");
    }
  }
{
    DynamicConverter<ILoggingEvent> converter=new CallerDataConverter();
    this.optionList.clear();
    this.optionList.add("2");
    this.optionList.add("XXX");
    this.optionList.add("+");
    converter.setOptionList(this.optionList);
    converter.start();
    StringBuilder buf=new StringBuilder();
    LoggingEvent event=makeLoggingEvent(null);
    event.setMarker(MarkerFactory.getMarker("YYY"));
    converter.write(buf,event);
    if (buf.length() < 10) {
      fail("buf is too short");
    }
  }
{
    DynamicConverter<ILoggingEvent> converter=new CallerDataConverter();
    this.optionList.clear();
    this.optionList.add("2");
    this.optionList.add("XXX");
    this.optionList.add("*");
    converter.setOptionList(this.optionList);
    converter.start();
    StringBuilder buf=new StringBuilder();
    converter.write(buf,le);
    if (buf.length() < 10) {
      fail("buf is too short");
    }
  }
}
