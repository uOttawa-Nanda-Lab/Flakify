/** 
 * Method: getServiceInfo(String stackName, String version, String serviceName)
 */
@Test public void getServiceInfo() throws Exception {
  ServiceInfo si=metaInfo.getServiceInfo(STACK_NAME_HDP,STACK_VERSION_HDP,SERVICE_NAME_HDFS);
  assertNotNull(si);
}
