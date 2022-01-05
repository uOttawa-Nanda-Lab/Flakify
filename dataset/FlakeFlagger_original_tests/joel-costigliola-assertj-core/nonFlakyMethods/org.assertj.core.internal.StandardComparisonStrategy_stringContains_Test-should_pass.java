@Test public void should_pass(){
  assertTrue(standardComparisonStrategy.stringContains("Frodo","ro"));
  assertFalse(standardComparisonStrategy.stringContains("rodo","Fr"));
}
