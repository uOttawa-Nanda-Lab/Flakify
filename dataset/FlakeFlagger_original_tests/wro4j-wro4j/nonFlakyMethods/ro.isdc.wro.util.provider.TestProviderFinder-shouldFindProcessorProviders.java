@Test public void shouldFindProcessorProviders(){
  Assert.assertNotNull(ProviderFinder.of(ProcessorProvider.class).find());
}
