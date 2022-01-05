@Test public void assertAddJobRegisterStatistics(){
  assertTrue(repository.add(new JobRegisterStatistics(100,new Date())));
}
