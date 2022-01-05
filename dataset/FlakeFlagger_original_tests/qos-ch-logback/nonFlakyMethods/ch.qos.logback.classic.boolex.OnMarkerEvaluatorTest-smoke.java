@Test public void smoke() throws EvaluationException {
  evaluator.addMarker("M");
  evaluator.start();
  event.setMarker(MarkerFactory.getMarker("M"));
  assertTrue(evaluator.evaluate(event));
}
