@Test public void assertAddJobRunningStatistics(){
  assertTrue(repository.add(new TaskRunningStatistics(100,new Date())));
}
