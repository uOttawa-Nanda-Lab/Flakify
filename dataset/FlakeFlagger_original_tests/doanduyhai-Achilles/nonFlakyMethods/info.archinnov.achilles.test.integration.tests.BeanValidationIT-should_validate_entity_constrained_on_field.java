@Test public void should_validate_entity_constrained_on_field() throws Exception {
  Long id=RandomUtils.nextLong();
  EntityWithFieldLevelConstraint entity=new EntityWithFieldLevelConstraint();
  entity.setId(id);
  entity.setName("name");
  manager.persist(entity);
  EntityWithFieldLevelConstraint found=manager.find(EntityWithFieldLevelConstraint.class,id);
  assertThat(found).isNotNull();
  assertThat(found.getName()).isEqualTo("name");
}
