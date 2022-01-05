@Test public void testFilter() throws Exception {
  ServiceConfig serviceConfig=new ServiceConfig();
  serviceConfig.setFilter("mockfilter");
  assertThat(serviceConfig.getFilter(),equalTo("mockfilter"));
  Map<String,String> parameters=new HashMap<String,String>();
  parameters.put(Constants.SERVICE_FILTER_KEY,"prefilter");
  AbstractServiceConfig.appendParameters(parameters,serviceConfig);
  assertThat(parameters,hasEntry(Constants.SERVICE_FILTER_KEY,"prefilter,mockfilter"));
}
