@Test public void testAdditive(){
  listAppender.start();
  root.addAppender(listAppender);
  loggerTest.addAppender(listAppender);
  loggerTest.setAdditive(false);
  loggerTest.debug("hello");
  assertEquals(1,listAppender.list.size());
}
