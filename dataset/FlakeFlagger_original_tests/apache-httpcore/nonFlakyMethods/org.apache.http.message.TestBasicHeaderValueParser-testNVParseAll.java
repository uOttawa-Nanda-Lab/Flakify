@Test public void testNVParseAll(){
  String s="test; test1 =  stuff   ; test2 =  \"stuff; stuff\"; test3=\"stuff";
  NameValuePair[] params=BasicHeaderValueParser.parseParameters(s,null);
  Assert.assertEquals("test",params[0].getName());
  Assert.assertEquals(null,params[0].getValue());
  Assert.assertEquals("test1",params[1].getName());
  Assert.assertEquals("stuff",params[1].getValue());
  Assert.assertEquals("test2",params[2].getName());
  Assert.assertEquals("stuff; stuff",params[2].getValue());
  Assert.assertEquals("test3",params[3].getName());
  Assert.assertEquals("\"stuff",params[3].getValue());
  s="  ";
  params=BasicHeaderValueParser.parseParameters(s,null);
  Assert.assertEquals(0,params.length);
}
