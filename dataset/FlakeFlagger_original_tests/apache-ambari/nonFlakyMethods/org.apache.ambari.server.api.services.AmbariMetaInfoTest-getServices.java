/** 
 * Method: Map<String, ServiceInfo> getServices(String stackName, String version, String serviceName)
 */
@Test public void getServices(){
  Map<String,ServiceInfo> services=metaInfo.getServices(STACK_NAME_HDP,STACK_VERSION_HDP);
  LOG.info("Getting all the services ");
  for (  Map.Entry<String,ServiceInfo> entry : services.entrySet()) {
    LOG.info("Service Name " + entry.getKey() + " values "+ entry.getValue());
  }
  assertTrue(services.containsKey("HDFS"));
  assertTrue(services.containsKey("MAPREDUCE"));
  assertNotNull(services);
  assertNotSame(services.keySet().size(),0);
}
