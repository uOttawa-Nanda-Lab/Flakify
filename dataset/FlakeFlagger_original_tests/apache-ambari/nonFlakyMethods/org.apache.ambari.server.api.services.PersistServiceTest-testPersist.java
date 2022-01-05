@Test public void testPersist() throws UniformInterfaceException, JSONException, IOException {
  ClientConfig clientConfig=new DefaultClientConfig();
  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
  client=Client.create(clientConfig);
  WebResource webResource=client.resource("http://localhost:9998/persist");
  webResource.post("{\"xyx\" : \"t\"}");
  LOG.info("Done posting to the server");
  String output=webResource.get(String.class);
  LOG.info("All key values " + output);
  Map<String,String> jsonOutput=StageUtils.fromJson(output,Map.class);
  String value=jsonOutput.get("xyx");
  Assert.assertEquals("t",value);
  webResource=client.resource("http://localhost:9998/persist/xyx");
  output=webResource.get(String.class);
  Assert.assertEquals("t",output);
  LOG.info("Value for xyx " + output);
}
