@Test public void marker() throws Exception {
  jee.setExpression("marker.contains(\"BLUE\")");
  jee.start();
  LoggingEvent event=makeLoggingEvent(null);
  event.setMarker(MarkerFactory.getMarker("BLUE"));
  assertTrue(jee.evaluate(event));
}
