/** 
 * Method: getSupportedServices(String stackName, String version)
 */
@Test public void getSupportedServices() throws Exception {
  List<ServiceInfo> services=metaInfo.getSupportedServices(STACK_NAME_HDP,STACK_VERSION_HDP);
  assertNotNull(services);
  assertNotSame(services.size(),0);
}
