@Test public void restrictToKeysAddFilterOnGivenKeys(){
  Set<String> keys=Collections.singleton("procDef");
  GetProcessInstancesPayload filter=restrictionApplier.restrictToKeys(keys);
  assertThat(filter.getProcessDefinitionKeys()).isEqualTo(keys);
}
