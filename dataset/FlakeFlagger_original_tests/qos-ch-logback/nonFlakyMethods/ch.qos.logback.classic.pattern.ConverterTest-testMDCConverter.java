@Test public void testMDCConverter() throws Exception {
  MDC.clear();
  MDC.put("someKey","someValue");
  MDCConverter converter=new MDCConverter();
  this.optionList.clear();
  this.optionList.add("someKey");
  converter.setOptionList(optionList);
  converter.start();
  ILoggingEvent event=makeLoggingEvent(null);
  String result=converter.convert(event);
  assertEquals("someValue",result);
}
