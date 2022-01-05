@Test public void testStateModifyExpiresCache() throws InterruptedException {
  StateRepository repository=new CachingStateRepository(delegate,10000);
  for (int i=0; i < 5; i++) {
    assertTrue(repository.getFeatureState(DummyFeature.TEST).isEnabled());
    Thread.sleep(10);
  }
  repository.setFeatureState(new FeatureState(DummyFeature.TEST,true));
  for (int i=0; i < 5; i++) {
    assertTrue(repository.getFeatureState(DummyFeature.TEST).isEnabled());
    Thread.sleep(10);
  }
  Mockito.verify(delegate,Mockito.times(2)).getFeatureState(DummyFeature.TEST);
  Mockito.verify(delegate).setFeatureState(Mockito.any(FeatureState.class));
  Mockito.verifyNoMoreInteractions(delegate);
}
