@Test public void should_validate_entity_constrained_on_class() throws Exception {
  Long id=RandomUtils.nextLong();
  EntityWithClassLevelConstraint entity=new EntityWithClassLevelConstraint();
  entity.setId(id);
  entity.setFirstname("firstname");
  entity.setLastname("lastname");
  manager.persist(entity);
  EntityWithClassLevelConstraint found=manager.find(EntityWithClassLevelConstraint.class,id);
  assertThat(found).isNotNull();
  assertThat(found.getFirstname()).isEqualTo("firstname");
  assertThat(found.getLastname()).isEqualTo("lastname");
}
