public void testJobQueryWithExceptions() throws Throwable {
  createJobWithoutExceptionMsg();
  Job job=managementService.createJobQuery().jobId(jobEntity.getId()).singleResult();
  assertNotNull(job);
  List<Job> list=managementService.createJobQuery().withException().list();
  assertEquals(1,list.size());
  deleteJobInDatabase();
  createJobWithoutExceptionStacktrace();
  job=managementService.createJobQuery().jobId(jobEntity.getId()).singleResult();
  assertNotNull(job);
  list=managementService.createJobQuery().withException().list();
  assertEquals(1,list.size());
  deleteJobInDatabase();
}
