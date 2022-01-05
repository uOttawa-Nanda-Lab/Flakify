@Test(expected=AchillesBeanMappingException.class) public void should_exception_when_abstract_class() throws Exception {
  Validator.validateInstantiable(AbstractClass.class);
}
