@Test public void maxAgeInThePastWithDateAndLastModifiedHeaders() throws Exception {
  String lastModifiedDate=formatDate(-2,TimeUnit.HOURS);
  RecordedRequest conditionalRequest=assertConditionallyCached(new MockResponse().addHeader("Date: " + formatDate(-120,TimeUnit.SECONDS)).addHeader("Last-Modified: " + lastModifiedDate).addHeader("Cache-Control: max-age=60"));
  List<String> headers=conditionalRequest.getHeaders();
  assertTrue(headers.contains("If-Modified-Since: " + lastModifiedDate));
}
