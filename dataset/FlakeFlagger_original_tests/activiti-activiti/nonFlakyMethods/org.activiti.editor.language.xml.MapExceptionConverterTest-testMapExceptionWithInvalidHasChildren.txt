@Test public void testMapExceptionWithInvalidHasChildren() throws Exception {
  resourceName="mapException/mapExceptionInvalidHasChildrenModel.bpmn";
  try {
    readXMLFile();
    fail("No exception is thrown for mapExecution with invalid boolean for hasChildren");
  }
 catch (  XMLException x) {
    assertTrue(x.getMessage().indexOf("is not valid boolean") != -1);
  }
catch (  Exception e) {
    fail("wrong exception thrown. XmlException expected, " + e.getClass() + " thrown");
  }
}
