@Test public void testAppendAsciiByteArray() throws Exception {
  String s1="stuff";
  String s2=" and more stuff";
  byte[] b1=s1.getBytes("US-ASCII");
  byte[] b2=s2.getBytes("US-ASCII");
  CharArrayBuffer buffer=new CharArrayBuffer(8);
  buffer.append(b1,0,b1.length);
  buffer.append(b2,0,b2.length);
  Assert.assertEquals("stuff and more stuff",buffer.toString());
}
