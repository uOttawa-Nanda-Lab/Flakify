@Test public void nullMarkerInEvaluator() throws EvaluationException {
  evaluator.addMarker("M");
  evaluator.start();
  assertFalse(evaluator.evaluate(event));
}
