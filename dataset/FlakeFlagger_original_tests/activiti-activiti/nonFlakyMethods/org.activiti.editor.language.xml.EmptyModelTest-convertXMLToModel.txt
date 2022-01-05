@Test public void convertXMLToModel() throws Exception {
  try {
    readXMLFile();
    fail("Expected xml exception");
  }
 catch (  XMLException e) {
  }
}
