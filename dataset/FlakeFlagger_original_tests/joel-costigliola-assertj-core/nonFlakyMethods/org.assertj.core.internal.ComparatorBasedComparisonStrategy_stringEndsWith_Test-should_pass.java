@Test public void should_pass(){
  assertTrue(caseInsensitiveComparisonStrategy.stringEndsWith("Frodo","do"));
  assertTrue(caseInsensitiveComparisonStrategy.stringEndsWith("Frodo","DO"));
  assertFalse(caseInsensitiveComparisonStrategy.stringEndsWith("Frodo","d"));
  assertFalse(caseInsensitiveComparisonStrategy.stringEndsWith("Frodo","Mr Frodo"));
}
