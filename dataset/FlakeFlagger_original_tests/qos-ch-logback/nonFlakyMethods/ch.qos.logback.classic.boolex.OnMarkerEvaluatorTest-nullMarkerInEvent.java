@Test public void nullMarkerInEvent() throws EvaluationException {
  evaluator.addMarker("M");
  evaluator.start();
  assertFalse(evaluator.evaluate(event));
}
