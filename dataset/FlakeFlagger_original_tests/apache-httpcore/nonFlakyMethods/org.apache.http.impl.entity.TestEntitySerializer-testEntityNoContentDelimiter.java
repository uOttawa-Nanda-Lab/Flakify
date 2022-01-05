@Test public void testEntityNoContentDelimiter() throws Exception {
  SessionOutputBuffer outbuffer=new SessionOutputBufferMock();
  HttpMessage message=new DummyHttpMessage();
  EntitySerializer entitywriter=new EntitySerializer(new StrictContentLengthStrategy());
  OutputStream outstream=entitywriter.doSerialize(outbuffer,message);
  Assert.assertNotNull(outstream);
  Assert.assertTrue(outstream instanceof IdentityOutputStream);
}
