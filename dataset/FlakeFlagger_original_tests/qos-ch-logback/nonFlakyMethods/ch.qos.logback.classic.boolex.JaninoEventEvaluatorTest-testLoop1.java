@Test public void testLoop1() throws Exception {
  jee.setExpression("timeStamp > 10");
  jee.start();
  loop(jee,"timestamp > 10]: ");
}
