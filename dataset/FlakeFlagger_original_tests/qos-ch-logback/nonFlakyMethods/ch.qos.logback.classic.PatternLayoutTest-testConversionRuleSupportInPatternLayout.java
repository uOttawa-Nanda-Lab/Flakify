@Test public void testConversionRuleSupportInPatternLayout() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "conversionRule/patternLayout0.xml");
  root.getAppender("LIST");
  String msg="Simon says";
  logger.debug(msg);
  StringListAppender<ILoggingEvent> sla=(StringListAppender<ILoggingEvent>)root.getAppender("LIST");
  assertNotNull(sla);
  assertEquals(1,sla.strList.size());
  assertEquals(SampleConverter.SAMPLE_STR + " - " + msg,sla.strList.get(0));
}
