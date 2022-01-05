@Test public void testWithoutLifeCycleRunning(){
  ApplicationLifeCycle applicationLifeCycle=mock(ApplicationLifeCycle.class);
  new ServiceBean<Object>().setApplicationContext(applicationLifeCycle);
  given(applicationLifeCycle.isRunning()).willReturn(false);
  Status status=springStatusChecker.check();
  assertThat(status.getLevel(),is(Status.Level.ERROR));
}
