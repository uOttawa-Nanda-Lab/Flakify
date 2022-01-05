@Test public void testBasicConstructor(){
  CharArrayBuffer buf=new CharArrayBuffer(32);
  buf.append("name: value");
  BufferedHeader header=new BufferedHeader(buf);
  Assert.assertEquals("name",header.getName());
  Assert.assertEquals("value",header.getValue());
  Assert.assertSame(buf,header.getBuffer());
  Assert.assertEquals(5,header.getValuePos());
}
