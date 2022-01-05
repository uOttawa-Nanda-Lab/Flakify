@Test public void testAppendCharArrayAsAscii() throws Exception {
  String s1="stuff";
  String s2=" and more stuff";
  char[] b1=s1.toCharArray();
  char[] b2=s2.toCharArray();
  ByteArrayBuffer buffer=new ByteArrayBuffer(8);
  buffer.append(b1,0,b1.length);
  buffer.append(b2,0,b2.length);
  Assert.assertEquals(s1 + s2,new String(buffer.toByteArray(),"US-ASCII"));
}
