@Test public void testEntityNoContentDelimiter() throws Exception {
  ContentLengthStrategy lenStrategy=new StrictContentLengthStrategy();
  HttpMessage message=new DummyHttpMessage();
  Assert.assertEquals(ContentLengthStrategy.IDENTITY,lenStrategy.determineLength(message));
}
