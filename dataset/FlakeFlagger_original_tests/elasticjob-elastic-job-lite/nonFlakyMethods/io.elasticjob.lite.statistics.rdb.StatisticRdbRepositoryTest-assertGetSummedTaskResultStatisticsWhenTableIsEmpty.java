@Test public void assertGetSummedTaskResultStatisticsWhenTableIsEmpty(){
  for (  StatisticInterval each : StatisticInterval.values()) {
    TaskResultStatistics po=repository.getSummedTaskResultStatistics(new Date(),each);
    assertThat(po.getSuccessCount(),is(0));
    assertThat(po.getFailedCount(),is(0));
  }
}
