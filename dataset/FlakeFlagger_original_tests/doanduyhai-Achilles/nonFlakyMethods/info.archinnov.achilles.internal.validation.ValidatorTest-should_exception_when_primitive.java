@Test(expected=AchillesBeanMappingException.class) public void should_exception_when_primitive() throws Exception {
  Validator.validateInstantiable(long.class);
}
