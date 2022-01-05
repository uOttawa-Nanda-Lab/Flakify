@Test public void replaceWithJoran() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "pattern/replace0.xml");
  StatusPrinter.print(lc);
  root.getAppender("LIST");
  String msg="And the number is 4111111111110000, expiring on 12/2010";
  logger.debug(msg);
  StringListAppender<ILoggingEvent> sla=(StringListAppender<ILoggingEvent>)root.getAppender("LIST");
  assertNotNull(sla);
  assertEquals(1,sla.strList.size());
  assertEquals("And the number is XXXX, expiring on 12/2010",sla.strList.get(0));
}
