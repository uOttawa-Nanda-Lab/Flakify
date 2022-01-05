@Test public void level() throws JoranException {
  configure(ClassicTestConstants.JORAN_INPUT_PREFIX + "simpleLevel.xml");
  ListAppender listAppender=(ListAppender)root.getAppender("LIST");
  assertEquals(0,listAppender.list.size());
  String msg="hello world";
  logger.debug(msg);
  assertEquals(0,listAppender.list.size());
}
