@Test public void shouldHaveAllFieldsSetCorrectlyAfterReset(){
  shouldComputeCorrectTotalResources();
  shouldComputeCorrectTotalNumberOfErrors();
  victim.reset();
  assertEquals(0,victim.getTotalResources());
  assertEquals(0,victim.getTotalFoundErrors());
  assertEquals(0,victim.getTotalResourcesWithErrors());
}
