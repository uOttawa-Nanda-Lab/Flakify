@Test public void assertFindLatestJobRegisterStatistics(){
  repository.add(new JobRegisterStatistics(100,new Date()));
  repository.add(new JobRegisterStatistics(200,new Date()));
  Optional<JobRegisterStatistics> po=repository.findLatestJobRegisterStatistics();
  assertThat(po.get().getRegisteredCount(),is(200));
}
