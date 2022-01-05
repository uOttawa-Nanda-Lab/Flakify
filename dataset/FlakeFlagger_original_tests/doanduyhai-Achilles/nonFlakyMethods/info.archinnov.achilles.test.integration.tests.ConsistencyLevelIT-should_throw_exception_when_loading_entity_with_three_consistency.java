@Test public void should_throw_exception_when_loading_entity_with_three_consistency() throws Exception {
  EntityWithWriteOneAndReadThreeConsistency bean=new EntityWithWriteOneAndReadThreeConsistency(id,"FN","LN");
  manager.persist(bean);
  expectedEx.expect(UnavailableException.class);
  expectedEx.expectMessage("Not enough replica available for query at consistency THREE (3 required but only 1 alive)");
  manager.find(EntityWithWriteOneAndReadThreeConsistency.class,id);
}
