@Test public void testGeneric() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setGeneric(true);
  assertThat(referenceConfig.isGeneric(),is(true));
  Map<String,String> parameters=new HashMap<String,String>();
  AbstractInterfaceConfig.appendParameters(parameters,referenceConfig);
  assertThat(parameters,hasKey("generic"));
}
