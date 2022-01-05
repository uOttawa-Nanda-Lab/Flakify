@Test(expected=AchillesBeanMappingException.class) public void should_exception_array_type() throws Exception {
  String[] array=new String[2];
  Validator.validateInstantiable(array.getClass());
}
