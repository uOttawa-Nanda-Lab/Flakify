@Test public void contextNameConverter(){
  ClassicConverter converter=new ContextNameConverter();
  LoggerContext lcOther=new LoggerContext();
  lcOther.setName("another");
  converter.setContext(lcOther);
  lc.setName("aValue");
  ILoggingEvent event=makeLoggingEvent(null);
  String result=converter.convert(event);
  assertEquals("aValue",result);
}
