@Test public void testSetLength() throws Exception {
  ByteArrayBuffer buffer=new ByteArrayBuffer(4);
  buffer.setLength(2);
  Assert.assertEquals(2,buffer.length());
}
