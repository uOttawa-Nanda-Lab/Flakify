@SuppressWarnings({"unchecked"}) @Test public void should_raise_exception_when_bean_validation_error_for_property() throws Exception {
  boolean exceptionRaised=false;
  CompleteBean entity=new CompleteBean();
  Path propertyPath=mock(Path.class);
  when(validator.validate(entity)).thenReturn(Sets.<ConstraintViolation<CompleteBean>>newHashSet(violation));
  when(violation.getLeafBean().getClass().getCanonicalName()).thenReturn("className");
  when(violation.getPropertyPath()).thenReturn(propertyPath);
  when(propertyPath.toString()).thenReturn("property");
  when(violation.getMessage()).thenReturn("violation");
  try {
    interceptor.onEvent(entity);
  }
 catch (  AchillesBeanValidationException ex) {
    assertThat(ex.getMessage()).isEqualTo("Bean validation error : \n\tproperty 'property' of class 'java.lang.String' violation\n");
    exceptionRaised=true;
  }
  assertThat(exceptionRaised).isTrue();
}
