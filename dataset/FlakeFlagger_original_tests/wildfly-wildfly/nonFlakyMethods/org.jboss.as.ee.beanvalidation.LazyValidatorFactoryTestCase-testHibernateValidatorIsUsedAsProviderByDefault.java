@Test public void testHibernateValidatorIsUsedAsProviderByDefault(){
  HibernateValidatorFactory hibernateValidatorFactory=validatorFactory.unwrap(HibernateValidatorFactory.class);
  assertNotNull("LazyValidatorFactory should delegate to the HV factory by default",hibernateValidatorFactory);
  Validator validator=validatorFactory.getValidator();
  assertNotNull("LazyValidatorFactory should provide a validator",validator);
}
