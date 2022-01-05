@Test public void nullMDC() throws EvaluationException {
  LoggingEvent event=makeEvent("x");
  doEvaluateAndCheck("e.mdc?.get('key') == 'val'",event,false);
}
