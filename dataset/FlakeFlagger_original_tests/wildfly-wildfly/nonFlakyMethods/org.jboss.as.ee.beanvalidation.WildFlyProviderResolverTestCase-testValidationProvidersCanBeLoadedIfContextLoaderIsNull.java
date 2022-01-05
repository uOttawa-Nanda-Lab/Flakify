@Test @WithContextClassLoader(NullClassLoader.class) public void testValidationProvidersCanBeLoadedIfContextLoaderIsNull(){
  List<ValidationProvider<?>> validationProviders=providerResolver.getValidationProviders();
  assertEquals(2,validationProviders.size());
}
