@Test public void testParseWrongJSSE() throws Exception {
  try {
    parse("securityParserError.xml");
    Assert.fail("There should have been an error.");
  }
 catch (  XMLStreamException ex) {
    Assert.assertTrue(ex.getMessage(),ex.getMessage().contains("WFLYSEC0023"));
  }
}
