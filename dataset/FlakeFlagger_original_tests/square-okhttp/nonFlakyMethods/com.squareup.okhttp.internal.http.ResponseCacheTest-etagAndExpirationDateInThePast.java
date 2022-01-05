@Test public void etagAndExpirationDateInThePast() throws Exception {
  String lastModifiedDate=formatDate(-2,TimeUnit.HOURS);
  RecordedRequest conditionalRequest=assertConditionallyCached(new MockResponse().addHeader("ETag: v1").addHeader("Last-Modified: " + lastModifiedDate).addHeader("Expires: " + formatDate(-1,TimeUnit.HOURS)));
  List<String> headers=conditionalRequest.getHeaders();
  assertTrue(headers.contains("If-None-Match: v1"));
  assertTrue(headers.contains("If-Modified-Since: " + lastModifiedDate));
}
