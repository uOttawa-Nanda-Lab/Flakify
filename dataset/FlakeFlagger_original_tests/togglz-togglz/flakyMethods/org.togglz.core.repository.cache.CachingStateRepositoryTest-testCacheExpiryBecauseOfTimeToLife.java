@Test public void testCacheExpiryBecauseOfTimeToLife() throws InterruptedException {
  long ttl=5;
  StateRepository repository=new CachingStateRepository(delegate,ttl);
  for (int i=0; i < 5; i++) {
    assertTrue(repository.getFeatureState(DummyFeature.TEST).isEnabled());
    Thread.sleep(ttl + 1);
  }
  Mockito.verify(delegate,Mockito.times(5)).getFeatureState(DummyFeature.TEST);
  Mockito.verifyNoMoreInteractions(delegate);
}
