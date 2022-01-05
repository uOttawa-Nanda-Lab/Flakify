@Test public void test_Constructor_WithErrorParam(){
  try {
    new RegistryDirectory(null,null);
    fail();
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    new RegistryDirectory(null,noMeaningUrl);
    fail();
  }
 catch (  IllegalArgumentException e) {
  }
  try {
    new RegistryDirectory(RegistryDirectoryTest.class,URL.valueOf("dubbo://10.20.30.40:9090"));
    fail();
  }
 catch (  IllegalArgumentException e) {
  }
}
