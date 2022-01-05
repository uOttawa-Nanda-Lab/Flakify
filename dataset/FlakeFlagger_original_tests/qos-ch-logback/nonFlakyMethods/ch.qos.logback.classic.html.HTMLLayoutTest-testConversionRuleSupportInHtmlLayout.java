@Test public void testConversionRuleSupportInHtmlLayout() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "conversionRule/htmlLayout0.xml");
  root.getAppender("LIST");
  String msg="Simon says";
  root.debug(msg);
  StringListAppender<ILoggingEvent> sla=(StringListAppender<ILoggingEvent>)root.getAppender("LIST");
  assertNotNull(sla);
  StatusPrinter.print(lc);
  assertEquals(1,sla.strList.size());
  assertFalse(sla.strList.get(0).contains("PARSER_ERROR"));
}
