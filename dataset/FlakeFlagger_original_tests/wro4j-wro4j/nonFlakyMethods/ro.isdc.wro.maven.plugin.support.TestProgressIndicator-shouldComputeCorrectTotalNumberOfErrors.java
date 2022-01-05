@Test public void shouldComputeCorrectTotalNumberOfErrors(){
  final int times=10;
  final int errorsPerResource=2;
  for (int i=0; i < times; i++) {
    victim.addFoundErrors(errorsPerResource);
  }
  assertEquals(times * errorsPerResource,victim.getTotalFoundErrors());
  assertEquals(times,victim.getTotalResourcesWithErrors());
}
