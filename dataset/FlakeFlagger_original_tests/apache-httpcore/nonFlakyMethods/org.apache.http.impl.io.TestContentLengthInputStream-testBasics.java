@Test public void testBasics() throws IOException {
  String correct="1234567890123456";
  InputStream in=new ContentLengthInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(correct,CONTENT_CHARSET)),10L);
  ByteArrayOutputStream out=new ByteArrayOutputStream();
  byte[] buffer=new byte[50];
  int len=in.read(buffer,0,2);
  out.write(buffer,0,len);
  len=in.read(buffer);
  out.write(buffer,0,len);
  String result=EncodingUtils.getString(out.toByteArray(),CONTENT_CHARSET);
  Assert.assertEquals(result,"1234567890");
}
