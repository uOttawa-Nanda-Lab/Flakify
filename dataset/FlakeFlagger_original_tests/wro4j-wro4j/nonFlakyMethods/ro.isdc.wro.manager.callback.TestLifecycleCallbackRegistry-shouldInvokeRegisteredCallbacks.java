@Test public void shouldInvokeRegisteredCallbacks(){
  final LifecycleCallback callback=Mockito.mock(LifecycleCallback.class);
  registry.registerCallback(factoryFor(callback));
  registry.onBeforeModelCreated();
  Mockito.verify(callback).onBeforeModelCreated();
  registry.onAfterModelCreated();
  Mockito.verify(callback).onAfterModelCreated();
  registry.onBeforePreProcess();
  Mockito.verify(callback).onBeforePreProcess();
  registry.onAfterPreProcess();
  Mockito.verify(callback).onAfterPreProcess();
  registry.onBeforePostProcess();
  Mockito.verify(callback).onBeforePostProcess();
  registry.onAfterPostProcess();
  Mockito.verify(callback).onAfterPostProcess();
  registry.onBeforeMerge();
  Mockito.verify(callback).onBeforeMerge();
  registry.onAfterMerge();
  Mockito.verify(callback).onAfterMerge();
  registry.onProcessingComplete();
  Mockito.verify(callback).onProcessingComplete();
}
