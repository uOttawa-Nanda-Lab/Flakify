@Test public void mdc() throws EvaluationException {
  MDC.put("key","val");
  LoggingEvent event=makeEvent("x");
  doEvaluateAndCheck("e.mdc['key'] == 'val'",event,true);
  MDC.clear();
}
