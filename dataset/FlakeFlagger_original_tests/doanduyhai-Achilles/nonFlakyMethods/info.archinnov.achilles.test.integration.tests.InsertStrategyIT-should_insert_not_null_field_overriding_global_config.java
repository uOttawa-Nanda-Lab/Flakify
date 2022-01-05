@Test public void should_insert_not_null_field_overriding_global_config() throws Exception {
  Long id=RandomUtils.nextLong();
  EntityWithNotNullInsertStrategy entity=new EntityWithNotNullInsertStrategy();
  entity.setId(id);
  entity.setName("Helen");
  entity.setLabel("label");
  manager1.persist(entity);
  entity.setLabel(null);
  manager1.persist(entity);
  final EntityWithNotNullInsertStrategy found=manager1.find(EntityWithNotNullInsertStrategy.class,id);
  assertThat(found.getName()).isEqualTo("Helen");
  assertThat(found.getLabel()).isEqualTo("label");
}
