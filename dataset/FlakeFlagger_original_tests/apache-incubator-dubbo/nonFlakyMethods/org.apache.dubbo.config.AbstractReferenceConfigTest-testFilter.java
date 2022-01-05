@Test public void testFilter() throws Exception {
  ReferenceConfig referenceConfig=new ReferenceConfig();
  referenceConfig.setFilter("mockfilter");
  assertThat(referenceConfig.getFilter(),equalTo("mockfilter"));
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put(Constants.REFERENCE_FILTER_KEY,"prefilter");
  AbstractInterfaceConfig.appendParameters(parameters,referenceConfig);
  assertThat(parameters,hasValue("prefilter,mockfilter"));
}
