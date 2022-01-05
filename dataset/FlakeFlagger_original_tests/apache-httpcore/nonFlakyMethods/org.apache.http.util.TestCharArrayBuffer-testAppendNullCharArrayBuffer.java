@Test public void testAppendNullCharArrayBuffer() throws Exception {
  CharArrayBuffer buffer=new CharArrayBuffer(8);
  buffer.append((CharArrayBuffer)null);
  buffer.append((CharArrayBuffer)null,0,0);
  Assert.assertEquals("",buffer.toString());
}
