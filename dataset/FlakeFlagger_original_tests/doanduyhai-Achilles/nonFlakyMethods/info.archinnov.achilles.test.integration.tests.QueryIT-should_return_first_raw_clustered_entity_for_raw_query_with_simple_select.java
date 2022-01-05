@Test public void should_return_first_raw_clustered_entity_for_raw_query_with_simple_select() throws Exception {
  Long id=RandomUtils.nextLong();
  ClusteredEntity entity=new ClusteredEntity(id,10,"name","value");
  manager.persist(entity);
  String queryString="SELECT id,count,name,value FROM " + TABLE_NAME + " LIMIT 3";
  ClusteredEntity actual=manager.rawTypedQuery(ClusteredEntity.class,queryString).getFirst();
  assertThat(actual).isNotNull();
  ClusteredKey clusteredKey=actual.getId();
  assertThat(clusteredKey).isNotNull();
  assertThat(clusteredKey.getId()).isEqualTo(id);
  assertThat(clusteredKey.getCount()).isEqualTo(10);
  assertThat(clusteredKey.getName()).isEqualTo("name");
}
