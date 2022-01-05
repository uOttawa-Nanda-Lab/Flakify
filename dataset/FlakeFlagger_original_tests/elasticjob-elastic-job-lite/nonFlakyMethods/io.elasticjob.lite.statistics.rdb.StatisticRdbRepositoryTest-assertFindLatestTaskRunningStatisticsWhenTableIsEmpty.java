@Test public void assertFindLatestTaskRunningStatisticsWhenTableIsEmpty(){
  assertFalse(repository.findLatestTaskRunningStatistics().isPresent());
}
