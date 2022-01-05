@Test public void should_error_on_invalid_class_persist() throws Exception {
  boolean exceptionRaised=false;
  Long id=RandomUtils.nextLong();
  EntityWithClassLevelConstraint entity=new EntityWithClassLevelConstraint();
  entity.setId(id);
  entity.setFirstname("fn");
  StringBuilder errorMessage=new StringBuilder("Bean validation error : \n");
  errorMessage.append("\tfirstname and lastname should not be blank for class '");
  errorMessage.append(EntityWithClassLevelConstraint.class.getCanonicalName()).append("'");
  try {
    manager.persist(entity);
  }
 catch (  AchillesBeanValidationException ex) {
    assertThat(ex.getMessage()).contains(errorMessage.toString());
    exceptionRaised=true;
  }
  assertThat(exceptionRaised).isTrue();
}
