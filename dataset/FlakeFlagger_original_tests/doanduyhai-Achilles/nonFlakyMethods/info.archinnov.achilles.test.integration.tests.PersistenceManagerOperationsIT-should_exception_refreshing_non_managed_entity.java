@Test public void should_exception_refreshing_non_managed_entity() throws Exception {
  CompleteBean completeBean=CompleteBeanTestBuilder.builder().randomId().name("name").buid();
  exception.expect(IllegalStateException.class);
  exception.expectMessage("The entity '" + completeBean + "' is not in 'managed' state");
  manager.refresh(completeBean);
}
