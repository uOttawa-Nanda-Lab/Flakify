@Test public void shouldFindNamingStrategyProviders(){
  victim=ProviderFinder.of(NamingStrategyProvider.class);
  assertFalse(victim.find().isEmpty());
}
