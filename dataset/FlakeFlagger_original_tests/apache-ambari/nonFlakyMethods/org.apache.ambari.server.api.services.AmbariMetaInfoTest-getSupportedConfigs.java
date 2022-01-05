/** 
 * Method: getSupportedConfigs(String stackName, String version, String serviceName)
 */
@Test public void getSupportedConfigs() throws Exception {
  Map<String,Map<String,String>> configsAll=metaInfo.getSupportedConfigs(STACK_NAME_HDP,STACK_VERSION_HDP,SERVICE_NAME_HDFS);
  Set<String> filesKeys=configsAll.keySet();
  for (  String file : filesKeys) {
    Map<String,String> configs=configsAll.get(file);
    Set<String> propertyKeys=configs.keySet();
    assertNotNull(propertyKeys);
    assertNotSame(propertyKeys.size(),0);
  }
}
