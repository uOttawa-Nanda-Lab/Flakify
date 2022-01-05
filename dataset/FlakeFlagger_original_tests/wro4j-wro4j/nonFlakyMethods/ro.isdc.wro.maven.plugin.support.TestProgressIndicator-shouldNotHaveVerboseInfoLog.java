@Test public void shouldNotHaveVerboseInfoLog(){
  final int times=5000;
  final AtomicInteger counter=new AtomicInteger();
  Mockito.doAnswer(new Answer<Void>(){
    public Void answer(    final InvocationOnMock invocation) throws Throwable {
      counter.incrementAndGet();
      invocation.callRealMethod();
      return null;
    }
  }
).when(log).info(Mockito.anyString());
  victim.setTimeoutDelay(1);
  for (int i=0; i < times; i++) {
    victim.onProcessingResource(generateRandomResource());
  }
  Assert.assertTrue(counter.get() < times);
}
