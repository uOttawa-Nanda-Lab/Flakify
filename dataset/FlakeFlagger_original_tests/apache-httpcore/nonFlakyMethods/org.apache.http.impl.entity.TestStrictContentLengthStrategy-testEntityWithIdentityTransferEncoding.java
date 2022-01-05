@Test public void testEntityWithIdentityTransferEncoding() throws Exception {
  ContentLengthStrategy lenStrategy=new StrictContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  message.addHeader("Transfer-Encoding","Identity");
  Assert.assertEquals(ContentLengthStrategy.IDENTITY,lenStrategy.determineLength(message));
}
