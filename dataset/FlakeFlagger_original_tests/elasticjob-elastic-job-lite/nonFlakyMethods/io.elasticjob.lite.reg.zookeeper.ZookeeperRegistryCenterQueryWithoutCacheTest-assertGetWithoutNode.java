@Test public void assertGetWithoutNode(){
  assertNull(zkRegCenter.get("/notExisted"));
}
