@Test public void testUnsupportedEncoding(){
  String s=constructString(SWISS_GERMAN_HELLO);
  byte[] b1=s.getBytes();
  byte[] b2=EncodingUtils.getBytes(s,"ThisJustAintRight");
  Assert.assertEquals(b1.length,b2.length);
  for (int i=0; i < b1.length; i++) {
    Assert.assertEquals(b1[i],b2[i]);
  }
  String s1=new String(b1);
  String s2=EncodingUtils.getString(b1,"ThisJustAintRight");
  Assert.assertEquals(s1,s2);
}
