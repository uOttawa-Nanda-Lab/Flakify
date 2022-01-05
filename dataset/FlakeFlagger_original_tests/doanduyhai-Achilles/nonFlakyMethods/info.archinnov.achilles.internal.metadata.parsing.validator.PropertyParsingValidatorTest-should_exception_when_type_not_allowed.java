@Test public void should_exception_when_type_not_allowed() throws Exception {
  Set<Class<?>> allowedTypes=new HashSet<>();
  allowedTypes.add(Long.class);
  allowedTypes.add(String.class);
  exception.expect(AchillesBeanMappingException.class);
  exception.expectMessage("msg1");
  PropertyParsingValidator.validateAllowedTypes(Integer.class,allowedTypes,"msg1");
  PropertyParsingValidator.validateAllowedTypes(CorrectEmbeddedKey.class,allowedTypes,"msg1");
  PropertyParsingValidator.validateAllowedTypes(CustomEnum.class,allowedTypes,"msg1");
}
