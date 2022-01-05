@Test public void testParseInvalidInput() throws Exception {
  try {
    ContentType.parse(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    ContentType.parse(";");
    Assert.fail("ParseException should have been thrown");
  }
 catch (  ParseException ex) {
  }
}
