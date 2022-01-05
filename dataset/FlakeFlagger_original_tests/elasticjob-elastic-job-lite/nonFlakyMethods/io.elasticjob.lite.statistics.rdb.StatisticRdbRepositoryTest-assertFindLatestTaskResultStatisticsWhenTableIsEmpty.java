@Test public void assertFindLatestTaskResultStatisticsWhenTableIsEmpty(){
  for (  StatisticInterval each : StatisticInterval.values()) {
    assertFalse(repository.findLatestTaskResultStatistics(each).isPresent());
  }
}
