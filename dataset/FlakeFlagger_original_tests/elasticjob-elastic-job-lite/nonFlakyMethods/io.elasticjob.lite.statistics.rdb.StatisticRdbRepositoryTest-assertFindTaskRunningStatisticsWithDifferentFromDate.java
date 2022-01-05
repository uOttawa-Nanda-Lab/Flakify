@Test public void assertFindTaskRunningStatisticsWithDifferentFromDate(){
  Date now=new Date();
  Date yesterday=getYesterday();
  assertTrue(repository.add(new TaskRunningStatistics(100,yesterday)));
  assertTrue(repository.add(new TaskRunningStatistics(100,now)));
  assertThat(repository.findTaskRunningStatistics(yesterday).size(),is(2));
  assertThat(repository.findTaskRunningStatistics(now).size(),is(1));
}
