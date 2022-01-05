@Test public void testAppendNullCharArray() throws Exception {
  ByteArrayBuffer buffer=new ByteArrayBuffer(8);
  buffer.append((char[])null,0,0);
  Assert.assertEquals(0,buffer.length());
}
