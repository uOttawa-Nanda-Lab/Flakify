@Test public void assertFindJobRunningStatisticsWhenTableIsEmpty(){
  assertThat(repository.findJobRunningStatistics(new Date()).size(),is(0));
}
