@Test public void testHibernateValidatorIsFirstProviderInList(){
  List<ValidationProvider<?>> validationProviders=providerResolver.getValidationProviders();
  assertEquals(2,validationProviders.size());
  assertEquals(HibernateValidator.class.getName(),validationProviders.get(0).getClass().getName());
  assertEquals(MyValidationProvider.class.getName(),validationProviders.get(1).getClass().getName());
}
