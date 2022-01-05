@Test public void assertFindJobRunningStatisticsWithDifferentFromDate(){
  Date now=new Date();
  Date yesterday=getYesterday();
  assertTrue(repository.add(new JobRunningStatistics(100,yesterday)));
  assertTrue(repository.add(new JobRunningStatistics(100,now)));
  assertThat(repository.findJobRunningStatistics(yesterday).size(),is(2));
  assertThat(repository.findJobRunningStatistics(now).size(),is(1));
}
