@Test public void denyAllShouldAddUnmatchableFilter(){
  GetProcessInstancesPayload filter=restrictionApplier.denyAll();
  assertThat(filter.getProcessDefinitionKeys()).hasSize(1);
  assertThat(filter.getProcessDefinitionKeys().iterator().next()).startsWith("missing-");
}
