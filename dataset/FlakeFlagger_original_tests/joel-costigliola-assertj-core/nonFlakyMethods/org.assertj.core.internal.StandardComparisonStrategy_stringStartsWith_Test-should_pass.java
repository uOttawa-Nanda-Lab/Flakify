@Test public void should_pass(){
  assertTrue(standardComparisonStrategy.stringStartsWith("Frodo","Fro"));
  assertFalse(standardComparisonStrategy.stringStartsWith("rodo","Fro"));
}
