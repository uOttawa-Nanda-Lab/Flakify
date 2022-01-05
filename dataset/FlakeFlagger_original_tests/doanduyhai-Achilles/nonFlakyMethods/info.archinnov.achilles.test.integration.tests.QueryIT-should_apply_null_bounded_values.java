@Test public void should_apply_null_bounded_values(){
  CompleteBean entity=builder().randomId().name("DuyHai").label("label").buid();
  manager.persist(entity);
  final Select.Where select=QueryBuilder.select().from("CompleteBean").where(QueryBuilder.eq("id",entity.getId()));
  final TypedQuery<CompleteBean> queryBuilder=manager.typedQuery(CompleteBean.class,select.getQueryString(),select.getValues());
  final CompleteBean actual=queryBuilder.getFirst();
  assertThat(actual.getLabel()).isEqualTo("label");
}
