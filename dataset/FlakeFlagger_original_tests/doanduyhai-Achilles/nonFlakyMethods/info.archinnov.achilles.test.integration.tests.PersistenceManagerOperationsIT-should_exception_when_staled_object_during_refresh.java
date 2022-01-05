@Test(expected=AchillesStaleObjectStateException.class) public void should_exception_when_staled_object_during_refresh() throws Exception {
  CompleteBean entity=CompleteBeanTestBuilder.builder().randomId().name("DuyHai").buid();
  entity=manager.persist(entity);
  session.execute("DELETE FROM completebean WHERE id=" + entity.getId());
  manager.refresh(entity);
}
