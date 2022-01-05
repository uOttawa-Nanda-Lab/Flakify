@Test public void withNullMarker_LBCORE_118() throws Exception {
  jee.setExpression("marker.contains(\"BLUE\")");
  jee.start();
  ILoggingEvent event=makeLoggingEvent(null);
  try {
    jee.evaluate(event);
    fail("We should not reach this point");
  }
 catch (  EvaluationException ee) {
  }
}
