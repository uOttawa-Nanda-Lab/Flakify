@Test public void isValidService(){
  boolean valid=metaInfo.isValidService(STACK_NAME_HDP,STACK_VERSION_HDP,SERVICE_NAME_HDFS);
  assertTrue(valid);
}
