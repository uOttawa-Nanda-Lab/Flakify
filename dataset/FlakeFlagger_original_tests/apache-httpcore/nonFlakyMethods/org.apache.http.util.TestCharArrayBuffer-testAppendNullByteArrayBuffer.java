@Test public void testAppendNullByteArrayBuffer() throws Exception {
  CharArrayBuffer buffer=new CharArrayBuffer(8);
  buffer.append((ByteArrayBuffer)null,0,0);
  Assert.assertEquals("",buffer.toString());
}
