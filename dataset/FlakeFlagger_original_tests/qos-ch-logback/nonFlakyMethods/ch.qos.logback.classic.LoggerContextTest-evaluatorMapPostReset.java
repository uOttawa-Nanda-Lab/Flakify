@Test public void evaluatorMapPostReset(){
  lc.reset();
  assertNotNull(lc.getObject(CoreConstants.EVALUATOR_MAP));
}
