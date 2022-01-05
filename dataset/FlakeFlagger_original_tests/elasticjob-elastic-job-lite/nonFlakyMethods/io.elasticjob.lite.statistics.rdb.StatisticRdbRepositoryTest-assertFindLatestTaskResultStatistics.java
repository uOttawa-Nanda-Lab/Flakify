@Test public void assertFindLatestTaskResultStatistics(){
  for (  StatisticInterval each : StatisticInterval.values()) {
    repository.add(new TaskResultStatistics(100,2,each,new Date()));
    repository.add(new TaskResultStatistics(200,5,each,new Date()));
    Optional<TaskResultStatistics> po=repository.findLatestTaskResultStatistics(each);
    assertThat(po.get().getSuccessCount(),is(200));
    assertThat(po.get().getFailedCount(),is(5));
  }
}
