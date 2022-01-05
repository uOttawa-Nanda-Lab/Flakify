@Test public void level() throws EvaluationException {
  LoggingEvent event=makeEvent("x");
  doEvaluateAndCheck("e.level == DEBUG",event,true);
}
