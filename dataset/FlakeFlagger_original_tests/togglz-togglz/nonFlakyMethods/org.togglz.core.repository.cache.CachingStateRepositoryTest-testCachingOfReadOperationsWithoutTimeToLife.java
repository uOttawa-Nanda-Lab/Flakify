@Test public void testCachingOfReadOperationsWithoutTimeToLife() throws InterruptedException {
  StateRepository repository=new CachingStateRepository(delegate,0);
  for (int i=0; i < 10; i++) {
    assertTrue(repository.getFeatureState(DummyFeature.TEST).isEnabled());
    Thread.sleep(10);
  }
  Mockito.verify(delegate).getFeatureState(DummyFeature.TEST);
  Mockito.verifyNoMoreInteractions(delegate);
}
