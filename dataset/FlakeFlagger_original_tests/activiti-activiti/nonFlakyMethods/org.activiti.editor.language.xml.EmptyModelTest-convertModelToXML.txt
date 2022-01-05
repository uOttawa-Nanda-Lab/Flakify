@Test public void convertModelToXML() throws Exception {
  try {
    readXMLFile();
    fail("Expected xml exception");
  }
 catch (  XMLException e) {
  }
}
