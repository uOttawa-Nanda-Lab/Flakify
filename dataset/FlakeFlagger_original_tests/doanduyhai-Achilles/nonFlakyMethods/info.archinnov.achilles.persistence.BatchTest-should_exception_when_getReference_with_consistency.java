@Test public void should_exception_when_getReference_with_consistency() throws Exception {
  exception.expect(AchillesException.class);
  exception.expectMessage("Runtime custom Consistency Level cannot be set for batch mode. Please set the Consistency Levels at batch start with 'startBatch(consistencyLevel)'");
  manager.getProxy(CompleteBean.class,11L,ONE);
}
