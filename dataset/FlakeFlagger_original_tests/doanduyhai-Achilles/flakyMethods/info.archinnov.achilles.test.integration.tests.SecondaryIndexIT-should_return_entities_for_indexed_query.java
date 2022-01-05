@Test public void should_return_entities_for_indexed_query() throws Exception {
  CompleteBean entity1=CompleteBeanTestBuilder.builder().randomId().name("DuyHai").buid();
  CompleteBean entity2=CompleteBeanTestBuilder.builder().randomId().name("John DOO").buid();
  manager.persist(entity1);
  manager.persist(entity2);
  IndexCondition condition=new IndexCondition("name","John DOO");
  List<CompleteBean> actual=manager.indexedQuery(CompleteBean.class,condition).get();
  assertThat(actual).hasSize(1);
  CompleteBean found1=actual.get(0);
  assertThat(found1).isNotNull();
}
