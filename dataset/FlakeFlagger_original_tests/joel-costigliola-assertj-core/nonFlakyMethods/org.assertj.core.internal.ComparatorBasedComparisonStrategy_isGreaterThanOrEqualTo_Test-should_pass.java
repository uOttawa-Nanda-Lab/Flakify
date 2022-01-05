@Test public void should_pass(){
  String string="stringA";
  String lesserUpperString="STRING";
  assertTrue(caseInsensitiveComparisonStrategy.isGreaterThanOrEqualTo(string,string));
  assertTrue(caseInsensitiveComparisonStrategy.isGreaterThanOrEqualTo(string,"STRINGA"));
  assertTrue(caseInsensitiveComparisonStrategy.isGreaterThanOrEqualTo(string,lesserUpperString));
  assertFalse(caseInsensitiveComparisonStrategy.isGreaterThanOrEqualTo(lesserUpperString,string));
  String lesserLowerString="string";
  assertTrue(caseInsensitiveComparisonStrategy.isGreaterThanOrEqualTo(string,lesserLowerString));
  assertFalse(caseInsensitiveComparisonStrategy.isGreaterThanOrEqualTo(lesserLowerString,string));
}
