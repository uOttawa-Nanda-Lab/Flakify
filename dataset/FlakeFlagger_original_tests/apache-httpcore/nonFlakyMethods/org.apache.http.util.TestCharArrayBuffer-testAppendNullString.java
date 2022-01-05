@Test public void testAppendNullString() throws Exception {
  CharArrayBuffer buffer=new CharArrayBuffer(8);
  buffer.append((String)null);
  Assert.assertEquals("null",buffer.toString());
}
