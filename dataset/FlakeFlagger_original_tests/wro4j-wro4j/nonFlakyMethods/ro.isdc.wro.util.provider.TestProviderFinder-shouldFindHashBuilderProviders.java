@Test public void shouldFindHashBuilderProviders(){
  victim=ProviderFinder.of(HashStrategyProvider.class);
  assertFalse(victim.find().isEmpty());
}
