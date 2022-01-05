@Test public void should_pass(){
  assertTrue(caseInsensitiveComparisonStrategy.stringStartsWith("Frodo","Fro"));
  assertTrue(caseInsensitiveComparisonStrategy.stringStartsWith("Frodo","FRO"));
  assertFalse(caseInsensitiveComparisonStrategy.stringStartsWith("rodo","Fro"));
  assertFalse(caseInsensitiveComparisonStrategy.stringStartsWith("rodo","rodoo"));
}
