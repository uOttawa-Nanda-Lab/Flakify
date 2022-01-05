@Test public void testHeaderElements(){
  CharArrayBuffer buf=new CharArrayBuffer(32);
  buf.append("name: element1 = value1, element2; param1 = value1, element3");
  BufferedHeader header=new BufferedHeader(buf);
  HeaderElement[] elements=header.getElements();
  Assert.assertNotNull(elements);
  Assert.assertEquals(3,elements.length);
  Assert.assertEquals("element1",elements[0].getName());
  Assert.assertEquals("value1",elements[0].getValue());
  Assert.assertEquals("element2",elements[1].getName());
  Assert.assertEquals(null,elements[1].getValue());
  Assert.assertEquals("element3",elements[2].getName());
  Assert.assertEquals(null,elements[2].getValue());
  Assert.assertEquals(1,elements[1].getParameters().length);
}
