@Test public void msgRegex() throws EvaluationException {
  LoggingEvent event=makeEvent("Hello world");
  doEvaluateAndCheck("e.message =~ /xyz|wor/",event,true);
  doEvaluateAndCheck("e.message ==~ /xyz|wor/",event,false);
}
