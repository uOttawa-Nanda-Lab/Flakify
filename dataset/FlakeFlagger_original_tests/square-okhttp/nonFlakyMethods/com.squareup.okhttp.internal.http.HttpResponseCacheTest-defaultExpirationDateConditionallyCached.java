@Test public void defaultExpirationDateConditionallyCached() throws Exception {
  String lastModifiedDate=formatDate(-115,TimeUnit.SECONDS);
  RecordedRequest conditionalRequest=assertConditionallyCached(new MockResponse().addHeader("Last-Modified: " + lastModifiedDate).addHeader("Date: " + formatDate(-15,TimeUnit.SECONDS)));
  List<String> headers=conditionalRequest.getHeaders();
  assertTrue(headers.contains("If-Modified-Since: " + lastModifiedDate));
}
