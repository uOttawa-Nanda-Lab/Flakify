@Test public void throwable_LBCLASSIC_155_II() throws EvaluationException {
  jee.setExpression("throwableProxy.getClassName().contains(\"IO\")");
  jee.start();
  LoggingEvent event=makeLoggingEvent(new IOException(""));
  assertTrue(jee.evaluate(event));
}
