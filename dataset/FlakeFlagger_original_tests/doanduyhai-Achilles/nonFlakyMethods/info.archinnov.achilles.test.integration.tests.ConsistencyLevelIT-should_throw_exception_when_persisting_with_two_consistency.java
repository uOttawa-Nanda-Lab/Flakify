@Test public void should_throw_exception_when_persisting_with_two_consistency() throws Exception {
  EntityWithTwoConsistency bean=new EntityWithTwoConsistency();
  bean.setId(id);
  bean.setName("name");
  expectedEx.expect(UnavailableException.class);
  expectedEx.expectMessage("Not enough replica available for query at consistency TWO (2 required but only 1 alive)");
  manager.persist(bean);
}
