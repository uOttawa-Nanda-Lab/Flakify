@Test public void etag() throws Exception {
  RecordedRequest conditionalRequest=assertConditionallyCached(new MockResponse().addHeader("ETag: v1"));
  assertTrue(conditionalRequest.getHeaders().contains("If-None-Match: v1"));
}
