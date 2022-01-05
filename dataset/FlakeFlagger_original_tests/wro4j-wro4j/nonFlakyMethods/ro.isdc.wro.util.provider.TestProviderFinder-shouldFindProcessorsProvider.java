@Test public void shouldFindProcessorsProvider(){
  victim=ProviderFinder.of(ProcessorProvider.class);
  assertFalse(victim.find().isEmpty());
}
