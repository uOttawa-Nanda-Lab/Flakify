@Test public void testWithLifeCycleRunning(){
  ApplicationLifeCycle applicationLifeCycle=mock(ApplicationLifeCycle.class);
  new ServiceBean<Object>().setApplicationContext(applicationLifeCycle);
  given(applicationLifeCycle.getConfigLocations()).willReturn(new String[]{"test1","test2"});
  given(applicationLifeCycle.isRunning()).willReturn(true);
  Status status=springStatusChecker.check();
  assertThat(status.getLevel(),is(Status.Level.OK));
  assertThat(status.getMessage(),is("test1,test2"));
}
