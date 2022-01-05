@Test public void testComplex() throws Exception {
  jee.setExpression("level >= INFO && x.matches(message) && marker.contains(\"BLUE\")");
  jee.addMatcher(matcherX);
  jee.start();
  LoggingEvent event=makeLoggingEvent(null);
  event.setMarker(MarkerFactory.getMarker("BLUE"));
  assertTrue(jee.evaluate(event));
}
