@Test public void assertFindJobRegisterStatisticsWithDifferentFromDate(){
  Date now=new Date();
  Date yesterday=getYesterday();
  assertTrue(repository.add(new JobRegisterStatistics(100,yesterday)));
  assertTrue(repository.add(new JobRegisterStatistics(100,now)));
  assertThat(repository.findJobRegisterStatistics(yesterday).size(),is(2));
  assertThat(repository.findJobRegisterStatistics(now).size(),is(1));
}
