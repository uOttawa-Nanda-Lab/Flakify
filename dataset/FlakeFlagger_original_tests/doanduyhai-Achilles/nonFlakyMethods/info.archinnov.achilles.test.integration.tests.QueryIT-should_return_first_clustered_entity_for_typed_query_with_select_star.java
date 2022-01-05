@Test public void should_return_first_clustered_entity_for_typed_query_with_select_star() throws Exception {
  Long id=RandomUtils.nextLong();
  ClusteredEntity entity=new ClusteredEntity(id,10,"name","value");
  manager.persist(entity);
  String queryString="SELECT * FROM " + TABLE_NAME + " LIMIT 3";
  ClusteredEntity actual=manager.typedQuery(ClusteredEntity.class,queryString).getFirst();
  assertThat(actual).isNotNull();
  assertThat(actual).isInstanceOf(Factory.class);
  ClusteredKey clusteredKey=actual.getId();
  assertThat(clusteredKey).isNotNull();
  assertThat(clusteredKey.getId()).isEqualTo(id);
  assertThat(clusteredKey.getCount()).isEqualTo(10);
  assertThat(clusteredKey.getName()).isEqualTo("name");
}
