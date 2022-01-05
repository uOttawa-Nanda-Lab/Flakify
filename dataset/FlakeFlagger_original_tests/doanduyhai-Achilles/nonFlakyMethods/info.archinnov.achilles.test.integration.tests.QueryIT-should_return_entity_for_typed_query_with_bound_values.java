@Test public void should_return_entity_for_typed_query_with_bound_values() throws Exception {
  CompleteBean entity=builder().randomId().name("DuyHai").buid();
  manager.persist(entity);
  String queryString="SELECT id,name,friends FROM CompleteBean WHERE id = ?";
  List<CompleteBean> actual=manager.typedQuery(CompleteBean.class,queryString,entity.getId()).get();
  assertThat(actual).hasSize(1);
  CompleteBean found=actual.get(0);
  assertThat(found.getName()).isEqualTo(entity.getName());
}
