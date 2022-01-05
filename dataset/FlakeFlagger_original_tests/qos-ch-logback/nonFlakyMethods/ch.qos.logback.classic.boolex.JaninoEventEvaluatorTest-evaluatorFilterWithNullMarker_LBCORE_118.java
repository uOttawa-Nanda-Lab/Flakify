@Test public void evaluatorFilterWithNullMarker_LBCORE_118() throws Exception {
  EvaluatorFilter<ILoggingEvent> ef=new EvaluatorFilter<ILoggingEvent>();
  ef.setContext(loggerContext);
  ef.setOnMatch(FilterReply.ACCEPT);
  ef.setOnMismatch(FilterReply.DENY);
  jee.setExpression("marker.contains(\"BLUE\")");
  jee.start();
  ef.setEvaluator(jee);
  ef.start();
  ILoggingEvent event=makeLoggingEvent(null);
  assertEquals(FilterReply.NEUTRAL,ef.decide(event));
}
