@Test public void should_error_on_invalid_field_update() throws Exception {
  boolean exceptionRaised=false;
  Long id=RandomUtils.nextLong();
  EntityWithFieldLevelConstraint entity=new EntityWithFieldLevelConstraint();
  entity.setId(id);
  entity.setName("name");
  StringBuilder errorMessage=new StringBuilder("Bean validation error : \n");
  errorMessage.append("\tproperty 'name' of class '");
  errorMessage.append(EntityWithFieldLevelConstraint.class.getCanonicalName()).append("'");
  EntityWithFieldLevelConstraint managedEntity=manager.persist(entity);
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
