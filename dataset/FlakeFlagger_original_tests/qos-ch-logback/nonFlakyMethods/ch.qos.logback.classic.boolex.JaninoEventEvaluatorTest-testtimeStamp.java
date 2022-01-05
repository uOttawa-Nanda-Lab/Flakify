@Test public void testtimeStamp() throws Exception {
  jee.setExpression("timeStamp > 10");
  jee.start();
  ILoggingEvent event=makeLoggingEvent(null);
  assertTrue(jee.evaluate(event));
}
