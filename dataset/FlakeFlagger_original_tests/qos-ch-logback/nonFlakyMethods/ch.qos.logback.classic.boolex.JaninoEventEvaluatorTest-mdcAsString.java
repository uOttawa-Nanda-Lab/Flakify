@Test public void mdcAsString() throws Exception {
  String k="key" + diff;
  MDC.put("key" + diff,"value" + diff);
  jee.setExpression("((String) mdc.get(\"" + k + "\")).contains(\"alue\")");
  jee.start();
  StatusPrinter.printInCaseOfErrorsOrWarnings(loggerContext);
  LoggingEvent event=makeLoggingEvent(null);
  assertTrue(jee.evaluate(event));
  MDC.remove(k);
}
