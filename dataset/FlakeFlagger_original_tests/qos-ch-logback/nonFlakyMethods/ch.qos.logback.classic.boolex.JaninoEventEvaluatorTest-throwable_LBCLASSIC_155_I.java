@Test public void throwable_LBCLASSIC_155_I() throws EvaluationException {
  jee.setExpression("throwable instanceof java.io.IOException");
  jee.start();
  LoggingEvent event=makeLoggingEvent(new IOException(""));
  assertTrue(jee.evaluate(event));
}
