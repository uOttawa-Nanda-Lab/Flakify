@Test public void testLevel() throws Exception {
  jee.setExpression("level > DEBUG");
  jee.start();
  ILoggingEvent event=makeLoggingEvent(null);
  assertTrue(jee.evaluate(event));
}
