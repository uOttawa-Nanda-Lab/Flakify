@Test public void notFullMatch() throws Exception {
  prepareMatches(true,false,true);
  assertThat(this.report.getConditionAndOutcomesBySource().get("a").isFullMatch(),equalTo(false));
}
