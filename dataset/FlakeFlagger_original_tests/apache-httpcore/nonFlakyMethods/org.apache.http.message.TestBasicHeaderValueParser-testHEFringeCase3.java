@Test public void testHEFringeCase3() throws Exception {
  String headerValue=",, ,, ,";
  HeaderElement[] elements=BasicHeaderValueParser.parseElements(headerValue,null);
  Assert.assertEquals("Number of elements",0,elements.length);
}
