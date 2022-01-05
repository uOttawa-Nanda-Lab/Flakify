@Test public void testParseHeaderElements() throws Exception {
  String headerValue="name1 = value1; name2; name3=\"value3\" , name4=value4; " + "name5=value5, name6= ; name7 = value7; name8 = \" value8\"";
  HeaderElement[] elements=BasicHeaderValueParser.parseElements(headerValue,null);
  Assert.assertEquals(3,elements.length);
  Assert.assertEquals("name1",elements[0].getName());
  Assert.assertEquals("value1",elements[0].getValue());
  Assert.assertEquals(2,elements[0].getParameters().length);
  Assert.assertEquals("name2",elements[0].getParameters()[0].getName());
  Assert.assertEquals(null,elements[0].getParameters()[0].getValue());
  Assert.assertEquals("name3",elements[0].getParameters()[1].getName());
  Assert.assertEquals("value3",elements[0].getParameters()[1].getValue());
  Assert.assertEquals("name4",elements[1].getName());
  Assert.assertEquals("value4",elements[1].getValue());
  Assert.assertEquals(1,elements[1].getParameters().length);
  Assert.assertEquals("name5",elements[1].getParameters()[0].getName());
  Assert.assertEquals("value5",elements[1].getParameters()[0].getValue());
  Assert.assertEquals("name6",elements[2].getName());
  Assert.assertEquals("",elements[2].getValue());
  Assert.assertEquals(2,elements[2].getParameters().length);
  Assert.assertEquals("name7",elements[2].getParameters()[0].getName());
  Assert.assertEquals("value7",elements[2].getParameters()[0].getValue());
  Assert.assertEquals("name8",elements[2].getParameters()[1].getName());
  Assert.assertEquals(" value8",elements[2].getParameters()[1].getValue());
}
