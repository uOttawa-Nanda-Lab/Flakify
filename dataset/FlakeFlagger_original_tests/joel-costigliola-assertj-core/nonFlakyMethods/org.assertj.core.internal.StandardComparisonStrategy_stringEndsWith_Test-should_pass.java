@Test public void should_pass(){
  assertTrue(standardComparisonStrategy.stringEndsWith("Frodo","do"));
  assertFalse(standardComparisonStrategy.stringEndsWith("Frodo","d"));
}
