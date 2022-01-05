@Test public void assertIsExisted(){
  assertTrue(zkRegCenter.isExisted("/test"));
  assertTrue(zkRegCenter.isExisted("/test/deep/nested"));
  assertFalse(zkRegCenter.isExisted("/notExisted"));
}
