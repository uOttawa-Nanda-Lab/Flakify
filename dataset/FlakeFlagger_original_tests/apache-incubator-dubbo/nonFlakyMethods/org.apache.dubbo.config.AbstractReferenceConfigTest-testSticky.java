@Test public void testSticky() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setSticky(true);
  Map<String,String> parameters=new HashMap<String,String>();
  AbstractInterfaceConfig.appendParameters(parameters,referenceConfig);
  assertThat(referenceConfig.getSticky(),is(true));
  assertThat(parameters,hasKey(Constants.CLUSTER_STICKY_KEY));
}
