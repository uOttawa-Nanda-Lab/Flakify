@Test public void should_error_on_invalid_property_update() throws Exception {
  boolean exceptionRaised=false;
  Long id=RandomUtils.nextLong();
  EntityWithPropertyLevelConstraint entity=new EntityWithPropertyLevelConstraint();
  entity.setId(id);
  entity.setName("name");
  StringBuilder errorMessage=new StringBuilder("Bean validation error : \n");
  errorMessage.append("\tproperty 'name' of class '");
  errorMessage.append(EntityWithPropertyLevelConstraint.class.getCanonicalName()).append("'");
  EntityWithPropertyLevelConstraint managedEntity=manager.persist(entity);
  try {
    managedEntity.setName(null);
    manager.update(managedEntity);
  }
 catch (  AchillesBeanValidationException ex) {
    assertThat(ex.getMessage()).contains(errorMessage.toString());
    exceptionRaised=true;
  }
  assertThat(exceptionRaised).isTrue();
}
