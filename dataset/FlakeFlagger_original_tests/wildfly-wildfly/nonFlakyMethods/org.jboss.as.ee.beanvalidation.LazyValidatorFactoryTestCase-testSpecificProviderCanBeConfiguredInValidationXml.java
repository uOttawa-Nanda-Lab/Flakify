@Test @WithContextClassLoader(TestClassLoader.class) public void testSpecificProviderCanBeConfiguredInValidationXml(){
  Validator validator=validatorFactory.getValidator();
  assertNotNull("LazyValidatorFactory should provide a validator",validator);
  assertTrue("Validator should be of type created by XML-configured provider",validator instanceof MyValidatorImpl);
}
