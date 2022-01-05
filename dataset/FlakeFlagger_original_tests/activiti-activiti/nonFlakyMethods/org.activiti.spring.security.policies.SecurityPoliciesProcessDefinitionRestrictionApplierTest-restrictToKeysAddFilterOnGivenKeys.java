@Test public void restrictToKeysAddFilterOnGivenKeys(){
  Set<String> keys=Collections.singleton("procDef");
  GetProcessDefinitionsPayload filter=restrictionApplier.restrictToKeys(keys);
  assertThat(filter.getProcessDefinitionKeys()).isEqualTo(keys);
}
