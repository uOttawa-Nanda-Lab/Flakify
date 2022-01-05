@Test public void callerData() throws EvaluationException {
  LoggingEvent event=makeEvent("x");
  doEvaluateAndCheck("e.callerData.find{ it.className =~ /junit/ }",event,true);
}
