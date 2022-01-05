@Test public void should_pass(){
  String string="stringA";
  String lesserUpperString="STRING";
  assertTrue(caseInsensitiveComparisonStrategy.isGreaterThan(string,lesserUpperString));
  assertFalse(caseInsensitiveComparisonStrategy.isGreaterThan(lesserUpperString,string));
  assertFalse(caseInsensitiveComparisonStrategy.isGreaterThan(string,string));
  assertFalse(caseInsensitiveComparisonStrategy.isGreaterThan(string,"STRINGA"));
  String lowerLesserString="string";
  assertTrue(caseInsensitiveComparisonStrategy.isGreaterThan(string,lowerLesserString));
  assertFalse(caseInsensitiveComparisonStrategy.isGreaterThan(lowerLesserString,string));
}
