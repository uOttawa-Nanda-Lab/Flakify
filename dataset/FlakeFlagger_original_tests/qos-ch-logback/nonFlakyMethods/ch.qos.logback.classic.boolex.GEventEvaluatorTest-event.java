@Test public void event() throws EvaluationException {
  ILoggingEvent event=makeEvent("x");
  event.getLoggerContextVO();
  doEvaluateAndCheck("e.message == 'x'",event,true);
}
