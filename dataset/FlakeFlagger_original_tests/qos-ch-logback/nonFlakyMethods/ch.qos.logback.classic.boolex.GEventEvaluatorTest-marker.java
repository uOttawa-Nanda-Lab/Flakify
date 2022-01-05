@Test public void marker() throws EvaluationException {
  LoggingEvent event=makeEvent("x");
  event.setMarker(markerA);
  doEvaluateAndCheck("e.marker?.name == 'A'",event,true);
}
