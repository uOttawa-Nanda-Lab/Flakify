@Test public void assertFindLatestJobRunningStatisticsWhenTableIsEmpty(){
  assertFalse(repository.findLatestJobRunningStatistics().isPresent());
}
