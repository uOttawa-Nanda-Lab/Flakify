@Test public void should_throw_empty_condition_exception_for_indexed_query() throws Exception {
  exception.expect(AchillesException.class);
  exception.expectMessage("Index condition should not be null");
  manager.indexedQuery(CompleteBean.class,null).get();
}
