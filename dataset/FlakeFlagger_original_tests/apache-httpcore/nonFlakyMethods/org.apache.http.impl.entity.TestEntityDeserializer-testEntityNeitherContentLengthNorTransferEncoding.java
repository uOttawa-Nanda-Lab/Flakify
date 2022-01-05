@Test public void testEntityNeitherContentLengthNorTransferEncoding() throws Exception {
  SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{'0'});
  HttpMessage message=new DummyHttpMessage();
  message.getParams().setBooleanParameter(CoreProtocolPNames.STRICT_TRANSFER_ENCODING,false);
  EntityDeserializer entitygen=new EntityDeserializer(new LaxContentLengthStrategy());
  HttpEntity entity=entitygen.deserialize(inbuffer,message);
  Assert.assertNotNull(entity);
  Assert.assertEquals(-1,entity.getContentLength());
  Assert.assertFalse(entity.isChunked());
  InputStream instream=entity.getContent();
  Assert.assertNotNull(instream);
  Assert.assertFalse(instream instanceof ContentLengthInputStream);
  Assert.assertFalse(instream instanceof ChunkedInputStream);
  Assert.assertTrue(instream instanceof IdentityInputStream);
}
