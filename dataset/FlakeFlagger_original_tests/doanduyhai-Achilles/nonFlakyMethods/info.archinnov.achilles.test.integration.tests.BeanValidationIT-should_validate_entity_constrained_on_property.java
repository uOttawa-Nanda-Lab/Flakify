@Test public void should_validate_entity_constrained_on_property() throws Exception {
  Long id=RandomUtils.nextLong();
  EntityWithPropertyLevelConstraint entity=new EntityWithPropertyLevelConstraint();
  entity.setId(id);
  entity.setName("name");
  manager.persist(entity);
  EntityWithPropertyLevelConstraint found=manager.find(EntityWithPropertyLevelConstraint.class,id);
  assertThat(found).isNotNull();
  assertThat(found.getName()).isEqualTo("name");
}
