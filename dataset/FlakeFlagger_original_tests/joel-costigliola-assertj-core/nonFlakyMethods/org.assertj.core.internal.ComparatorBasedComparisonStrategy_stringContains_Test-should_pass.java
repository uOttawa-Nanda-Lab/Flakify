@Test public void should_pass(){
  assertTrue(caseInsensitiveComparisonStrategy.stringContains("Frodo","ro"));
  assertTrue(caseInsensitiveComparisonStrategy.stringContains("Frodo","RO"));
  assertTrue(caseInsensitiveComparisonStrategy.stringContains("Frodo",""));
  assertFalse(caseInsensitiveComparisonStrategy.stringContains("Frodo","Fra"));
  assertFalse(caseInsensitiveComparisonStrategy.stringContains("Frodo","Frodoo"));
  assertFalse(caseInsensitiveComparisonStrategy.stringContains("Frodo","Froda"));
  assertFalse(caseInsensitiveComparisonStrategy.stringContains("Frodo","abcdefg"));
  assertFalse(caseInsensitiveComparisonStrategy.stringContains("Frodo","a"));
}
