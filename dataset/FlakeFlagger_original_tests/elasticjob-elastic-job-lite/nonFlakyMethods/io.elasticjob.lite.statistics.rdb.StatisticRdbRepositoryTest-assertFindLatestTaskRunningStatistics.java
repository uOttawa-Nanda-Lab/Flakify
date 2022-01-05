@Test public void assertFindLatestTaskRunningStatistics(){
  repository.add(new TaskRunningStatistics(100,new Date()));
  repository.add(new TaskRunningStatistics(200,new Date()));
  Optional<TaskRunningStatistics> po=repository.findLatestTaskRunningStatistics();
  assertThat(po.get().getRunningCount(),is(200));
}
