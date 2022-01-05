@Test public void assertGetSummedTaskResultStatistics(){
  for (  StatisticInterval each : StatisticInterval.values()) {
    Date date=new Date();
    repository.add(new TaskResultStatistics(100,2,each,date));
    repository.add(new TaskResultStatistics(200,5,each,date));
    TaskResultStatistics po=repository.getSummedTaskResultStatistics(date,each);
    assertThat(po.getSuccessCount(),is(300));
    assertThat(po.getFailedCount(),is(7));
  }
}
