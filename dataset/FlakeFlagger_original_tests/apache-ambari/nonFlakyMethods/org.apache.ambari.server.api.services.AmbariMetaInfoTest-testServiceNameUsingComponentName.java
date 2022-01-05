@Test public void testServiceNameUsingComponentName(){
  String serviceName=metaInfo.getComponentToService(STACK_NAME_HDP,STACK_VERSION_HDP,"NAMENODE");
  assertTrue("HDFS".equals(serviceName));
}
