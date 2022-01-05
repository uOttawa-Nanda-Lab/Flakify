@Test public void testEntityWithChunkTransferEncoding() throws Exception {
  ContentLengthStrategy lenStrategy=new StrictContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Transfer-Encoding","Chunked");
  Assert.assertEquals(ContentLengthStrategy.CHUNKED,lenStrategy.determineLength(message));
}
