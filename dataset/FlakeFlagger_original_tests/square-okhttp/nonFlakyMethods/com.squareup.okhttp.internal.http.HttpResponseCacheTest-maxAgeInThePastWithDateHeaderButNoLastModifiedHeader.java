@Test public void maxAgeInThePastWithDateHeaderButNoLastModifiedHeader() throws Exception {
  assertNotCached(new MockResponse().addHeader("Date: " + formatDate(-120,TimeUnit.SECONDS)).addHeader("Cache-Control: max-age=60"));
}
