@Test public void should_pass(){
  String string="string";
  String greaterString="STRINGA";
  assertTrue(caseInsensitiveComparisonStrategy.isLessThan(string,greaterString));
  assertFalse(caseInsensitiveComparisonStrategy.isLessThan(greaterString,string));
  assertFalse(caseInsensitiveComparisonStrategy.isLessThan(string,string));
  assertFalse(caseInsensitiveComparisonStrategy.isLessThan(string,"STRING"));
  String lowerString="stringA";
  assertTrue(caseInsensitiveComparisonStrategy.isLessThan(string,lowerString));
  assertFalse(caseInsensitiveComparisonStrategy.isLessThan(lowerString,string));
}
