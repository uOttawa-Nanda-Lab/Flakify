@Test public void fullMatch() throws Exception {
  prepareMatches(true,true,true);
  assertThat(this.report.getConditionAndOutcomesBySource().get("a").isFullMatch(),equalTo(true));
}
