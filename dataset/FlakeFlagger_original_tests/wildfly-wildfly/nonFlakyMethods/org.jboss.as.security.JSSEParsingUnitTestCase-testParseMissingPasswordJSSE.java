@Test public void testParseMissingPasswordJSSE() throws Exception {
  try {
    parse("securityErrorMissingPassword.xml");
    Assert.fail("There should have been an error.");
  }
 catch (  XMLStreamException ex) {
    Assert.assertTrue(ex.getMessage(),ex.getMessage().contains("WFLYSEC0023"));
  }
}
