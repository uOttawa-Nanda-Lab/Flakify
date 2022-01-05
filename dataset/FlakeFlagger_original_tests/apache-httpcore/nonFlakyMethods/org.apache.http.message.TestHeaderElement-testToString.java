@Test public void testToString(){
  String s="name=value; param1=value1; param2=value2";
  HeaderElement element=BasicHeaderValueParser.parseHeaderElement(s,null);
  Assert.assertEquals(s,element.toString());
  s="name; param1=value1; param2=value2";
  element=BasicHeaderValueParser.parseHeaderElement(s,null);
  Assert.assertEquals(s,element.toString());
}
