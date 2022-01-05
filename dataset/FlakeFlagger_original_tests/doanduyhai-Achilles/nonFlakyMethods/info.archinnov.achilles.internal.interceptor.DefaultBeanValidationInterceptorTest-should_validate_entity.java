@Test public void should_validate_entity() throws Exception {
  CompleteBean entity=new CompleteBean();
  when(validator.validate(entity)).thenReturn(new HashSet<ConstraintViolation<CompleteBean>>());
  interceptor.onEvent(entity);
}
