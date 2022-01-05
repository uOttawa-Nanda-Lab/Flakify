@Test public void testMapExceptionWithNoErrorCode() throws Exception {
  resourceName="mapException/mapExceptionNoErrorCode.bpmn";
  try {
    readXMLFile();
    fail("No exception is thrown for mapExecution with no Error Code");
  }
 catch (  XMLException x) {
    assertTrue(x.getMessage().indexOf("No errorCode defined") != -1);
  }
catch (  Exception e) {
    fail("wrong exception thrown. XmlException expected, " + e.getClass() + " thrown");
  }
}
