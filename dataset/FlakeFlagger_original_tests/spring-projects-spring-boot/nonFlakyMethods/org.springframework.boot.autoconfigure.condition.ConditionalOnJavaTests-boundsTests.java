@Test public void boundsTests() throws Exception {
  testBounds(Range.EQUAL_OR_NEWER,JavaVersion.SEVEN,JavaVersion.SIX,true);
  testBounds(Range.EQUAL_OR_NEWER,JavaVersion.SEVEN,JavaVersion.SEVEN,true);
  testBounds(Range.EQUAL_OR_NEWER,JavaVersion.SEVEN,JavaVersion.EIGHT,false);
  testBounds(Range.OLDER_THAN,JavaVersion.SEVEN,JavaVersion.SIX,false);
  testBounds(Range.OLDER_THAN,JavaVersion.SEVEN,JavaVersion.SEVEN,false);
  testBounds(Range.OLDER_THAN,JavaVersion.SEVEN,JavaVersion.EIGHT,true);
}
