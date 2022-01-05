@Test public void testStacks() throws UniformInterfaceException, JSONException, IOException {
  ClientConfig clientConfig=new DefaultClientConfig();
  clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
  client=Client.create(clientConfig);
  WebResource webResource=client.resource("http://localhost:9998/stacks");
  String output=webResource.get(String.class);
  LOG.info("All Stack Info \n" + output);
  ObjectMapper mapper=new ObjectMapper();
  mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,false);
  List<StackInfo> stackInfos=mapper.readValue(output,new TypeReference<List<StackInfo>>(){
  }
);
  StackInfo stackInfo=stackInfos.get(0);
  Assert.assertEquals("HDP",stackInfo.getName());
  webResource=client.resource("http://localhost:9998/stacks/" + "HDP/version/0.1/services/HDFS");
  output=webResource.get(String.class);
  ServiceInfo info=mapper.readValue(output,ServiceInfo.class);
  Assert.assertEquals("HDFS",info.getName());
}
