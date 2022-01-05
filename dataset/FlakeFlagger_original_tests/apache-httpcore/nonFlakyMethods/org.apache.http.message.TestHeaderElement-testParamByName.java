@Test public void testParamByName() throws Exception {
  String s="name = value; param1 = value1; param2 = value2";
  HeaderElement element=BasicHeaderValueParser.parseHeaderElement(s,null);
  Assert.assertEquals("value1",element.getParameterByName("param1").getValue());
  Assert.assertEquals("value2",element.getParameterByName("param2").getValue());
  Assert.assertNull(element.getParameterByName("param3"));
  try {
    element.getParameterByName(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
