@Test public void testEntityWithIdentityTransferEncoding() throws Exception {
  SessionOutputBuffer outbuffer=new SessionOutputBufferMock();
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Transfer-Encoding","Identity");
  EntitySerializer entitywriter=new EntitySerializer(new StrictContentLengthStrategy());
  OutputStream outstream=entitywriter.doSerialize(outbuffer,message);
  Assert.assertNotNull(outstream);
  Assert.assertTrue(outstream instanceof IdentityOutputStream);
}
