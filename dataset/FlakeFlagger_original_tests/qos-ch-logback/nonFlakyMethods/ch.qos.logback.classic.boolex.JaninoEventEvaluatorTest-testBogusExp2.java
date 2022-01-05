@Test public void testBogusExp2(){
  jee.setExpression("mdc.get(\"keyXN89\").equals(null)");
  jee.setName("bogus");
  jee.start();
  assertTrue(jee.isStarted());
  ILoggingEvent event=makeLoggingEvent(null);
  for (int i=0; i < JaninoEventEvaluatorBase.ERROR_THRESHOLD; i++) {
    try {
      jee.evaluate(event);
      fail("should throw an exception");
    }
 catch (    EvaluationException e) {
    }
  }
  assertFalse(jee.isStarted());
}
