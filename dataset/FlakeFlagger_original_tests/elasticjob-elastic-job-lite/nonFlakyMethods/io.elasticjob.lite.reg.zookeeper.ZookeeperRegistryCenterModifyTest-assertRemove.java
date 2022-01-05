@Test public void assertRemove(){
  zkRegCenter.remove("/test");
  assertFalse(zkRegCenter.isExisted("/test"));
}
