@Test public void invoke() throws Exception {
  Report report=getEndpointBean().invoke();
  assertTrue(report.getPositiveMatches().isEmpty());
  assertTrue(report.getNegativeMatches().containsKey("a"));
}
