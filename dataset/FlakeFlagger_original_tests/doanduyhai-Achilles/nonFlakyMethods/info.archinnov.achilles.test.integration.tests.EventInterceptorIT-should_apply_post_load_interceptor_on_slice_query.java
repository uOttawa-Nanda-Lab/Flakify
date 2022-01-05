@Test public void should_apply_post_load_interceptor_on_slice_query() throws Exception {
  Long id=RandomUtils.nextLong();
  Integer count=RandomUtils.nextInt();
  String name=RandomStringUtils.randomAlphabetic(10);
  String value="value_before_load";
  ClusteredEntity entity=new ClusteredEntity(id,count,name,value);
  manager3.persist(entity);
  final List<ClusteredEntity> clusteredEntities=manager3.sliceQuery(ClusteredEntity.class).partitionComponents(id).get(10);
  assertThat(clusteredEntities.get(0).getValue()).isEqualTo("postLoad");
}
