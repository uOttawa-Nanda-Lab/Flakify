@Test public void should_pass(){
  String string="string";
  String greaterString="STRINGA";
  assertTrue(caseInsensitiveComparisonStrategy.isLessThanOrEqualTo(string,greaterString));
  assertTrue(caseInsensitiveComparisonStrategy.isLessThanOrEqualTo(string,"STRING"));
  assertTrue(caseInsensitiveComparisonStrategy.isLessThanOrEqualTo(string,string));
  assertFalse(caseInsensitiveComparisonStrategy.isLessThanOrEqualTo(greaterString,string));
  String lowerString="stringA";
  assertTrue(caseInsensitiveComparisonStrategy.isLessThanOrEqualTo(string,lowerString));
  assertFalse(caseInsensitiveComparisonStrategy.isLessThanOrEqualTo(lowerString,string));
}
