@Test public void should_error_on_invalid_field_persist() throws Exception {
  boolean exceptionRaised=false;
  Long id=RandomUtils.nextLong();
  EntityWithFieldLevelConstraint entity=new EntityWithFieldLevelConstraint();
  entity.setId(id);
  StringBuilder errorMessage=new StringBuilder("Bean validation error : \n");
  errorMessage.append("\tproperty 'name' of class '");
  errorMessage.append(EntityWithFieldLevelConstraint.class.getCanonicalName()).append("'");
  try {
    manager.persist(entity);
  }
 catch (  AchillesBeanValidationException ex) {
    assertThat(ex.getMessage()).contains(errorMessage.toString());
    exceptionRaised=true;
  }
  assertThat(exceptionRaised).isTrue();
}
