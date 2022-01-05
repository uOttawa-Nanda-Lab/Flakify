@Test(expected=AchillesBeanMappingException.class) public void should_exception_when_interface() throws Exception {
  Validator.validateInstantiable(TestInterface.class);
}
