@Test public void should_throw_clustered_exception_for_indexed_query() throws Exception {
  IndexCondition condition=new IndexCondition("name","John DOO");
  exception.expect(AchillesException.class);
  exception.expectMessage("Index query is not supported for clustered entity");
  manager.indexedQuery(ClusteredEntity.class,condition).get();
}
