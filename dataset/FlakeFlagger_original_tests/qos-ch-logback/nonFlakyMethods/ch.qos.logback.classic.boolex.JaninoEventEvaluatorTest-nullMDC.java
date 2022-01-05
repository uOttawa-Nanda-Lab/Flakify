@Test public void nullMDC() throws EvaluationException {
  MDC.clear();
  jee.setExpression("mdc.isEmpty()");
  jee.start();
  LoggingEvent event=makeLoggingEvent(null);
  assertTrue(jee.evaluate(event));
}
