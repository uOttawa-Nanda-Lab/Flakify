@Test public void testBasics() throws Exception {
  ByteArrayOutputStream buffer=new ByteArrayOutputStream();
  SessionOutputBufferMock datatransmitter=new SessionOutputBufferMock(buffer);
  OutputStream out=new ContentLengthOutputStream(datatransmitter,15L);
  byte[] tmp=new byte[10];
  out.write(tmp,0,10);
  out.write(1);
  out.write(tmp,0,10);
  out.write(tmp,0,10);
  out.write(tmp);
  out.write(1);
  out.write(2);
  out.flush();
  out.close();
  byte[] data=datatransmitter.getData();
  Assert.assertEquals(15,data.length);
}
