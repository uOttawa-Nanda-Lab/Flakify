@Test public void clientSuppliedIfNoneMatchSinceWithCachedResult() throws Exception {
  String lastModifiedDate=formatDate(-3,TimeUnit.MINUTES);
  MockResponse response=new MockResponse().addHeader("Last-Modified: " + lastModifiedDate).addHeader("Date: " + formatDate(-2,TimeUnit.MINUTES)).addHeader("Cache-Control: max-age=0");
  RecordedRequest request=assertClientSuppliedCondition(response,"If-None-Match","v1");
  List<String> headers=request.getHeaders();
  assertTrue(headers.contains("If-None-Match: v1"));
  assertFalse(headers.contains("If-Modified-Since: " + lastModifiedDate));
}
