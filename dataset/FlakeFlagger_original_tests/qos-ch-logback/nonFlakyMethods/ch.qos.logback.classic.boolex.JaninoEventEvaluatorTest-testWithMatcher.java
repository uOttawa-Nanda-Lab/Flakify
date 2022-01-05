@Test public void testWithMatcher() throws Exception {
  jee.setExpression("x.matches(message)");
  jee.addMatcher(matcherX);
  jee.start();
  ILoggingEvent event=makeLoggingEvent(null);
  assertTrue(jee.evaluate(event));
}
