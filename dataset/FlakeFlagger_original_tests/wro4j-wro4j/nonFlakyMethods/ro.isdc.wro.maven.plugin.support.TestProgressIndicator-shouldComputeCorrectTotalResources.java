@Test public void shouldComputeCorrectTotalResources(){
  final int times=10;
  for (int i=0; i < times; i++) {
    victim.onProcessingResource(generateRandomResource());
  }
  assertEquals(times,victim.getTotalResources());
}
