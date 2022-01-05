@Test public void testNVParseEscaped(){
  String s="test1 =  \"\\\"stuff\\\"\"; test2= \"\\\\\"; test3 = \"stuff; stuff\"";
  NameValuePair[] params=BasicHeaderValueParser.parseParameters(s,null);
  Assert.assertEquals(3,params.length);
  Assert.assertEquals("test1",params[0].getName());
  Assert.assertEquals("\\\"stuff\\\"",params[0].getValue());
  Assert.assertEquals("test2",params[1].getName());
  Assert.assertEquals("\\\\",params[1].getValue());
  Assert.assertEquals("test3",params[2].getName());
  Assert.assertEquals("stuff; stuff",params[2].getValue());
}
