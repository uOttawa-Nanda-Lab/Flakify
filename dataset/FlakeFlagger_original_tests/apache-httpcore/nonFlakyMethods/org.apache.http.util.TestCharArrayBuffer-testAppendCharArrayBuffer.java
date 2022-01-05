@Test public void testAppendCharArrayBuffer() throws Exception {
  CharArrayBuffer buffer1=new CharArrayBuffer(8);
  buffer1.append(" and more stuff");
  CharArrayBuffer buffer2=new CharArrayBuffer(8);
  buffer2.append("stuff");
  buffer2.append(buffer1);
  Assert.assertEquals("stuff and more stuff",buffer2.toString());
}
