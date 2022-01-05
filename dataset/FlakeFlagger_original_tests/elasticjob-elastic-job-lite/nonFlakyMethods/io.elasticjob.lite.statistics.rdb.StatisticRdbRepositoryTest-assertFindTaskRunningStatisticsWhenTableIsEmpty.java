@Test public void assertFindTaskRunningStatisticsWhenTableIsEmpty(){
  assertThat(repository.findTaskRunningStatistics(new Date()).size(),is(0));
}
