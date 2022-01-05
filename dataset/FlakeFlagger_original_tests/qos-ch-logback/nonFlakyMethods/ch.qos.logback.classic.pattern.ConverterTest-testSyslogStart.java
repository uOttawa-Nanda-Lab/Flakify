@Test public void testSyslogStart() throws Exception {
  DynamicConverter<ILoggingEvent> converter=new SyslogStartConverter();
  this.optionList.clear();
  this.optionList.add("MAIL");
  converter.setOptionList(this.optionList);
  converter.start();
  ILoggingEvent event=makeLoggingEvent(null);
  StringBuilder buf=new StringBuilder();
  converter.write(buf,event);
  String expected="<" + (SyslogConstants.LOG_MAIL + SyslogConstants.INFO_SEVERITY) + ">";
  assertTrue(buf.toString().startsWith(expected));
}
