@Test public void assertFindLatestJobRunningStatistics(){
  repository.add(new JobRunningStatistics(100,new Date()));
  repository.add(new JobRunningStatistics(200,new Date()));
  Optional<JobRunningStatistics> po=repository.findLatestJobRunningStatistics();
  assertThat(po.get().getRunningCount(),is(200));
}
