@Test public void nullMarker() throws EvaluationException {
  LoggingEvent event=makeEvent("x");
  doEvaluateAndCheck("e.marker?.name == 'YELLOW'",event,false);
}
