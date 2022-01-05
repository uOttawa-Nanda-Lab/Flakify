@Test public void testEntityWithContentLength() throws Exception {
  SessionOutputBuffer outbuffer=new SessionOutputBufferMock();
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Content-Length","100");
  EntitySerializer entitywriter=new EntitySerializer(new StrictContentLengthStrategy());
  OutputStream outstream=entitywriter.doSerialize(outbuffer,message);
  Assert.assertNotNull(outstream);
  Assert.assertTrue(outstream instanceof ContentLengthOutputStream);
}
