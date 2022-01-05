@Test public void assertFindTaskResultStatisticsWhenTableIsEmpty(){
  assertThat(repository.findTaskResultStatistics(new Date(),StatisticInterval.MINUTE).size(),is(0));
  assertThat(repository.findTaskResultStatistics(new Date(),StatisticInterval.HOUR).size(),is(0));
  assertThat(repository.findTaskResultStatistics(new Date(),StatisticInterval.DAY).size(),is(0));
}
