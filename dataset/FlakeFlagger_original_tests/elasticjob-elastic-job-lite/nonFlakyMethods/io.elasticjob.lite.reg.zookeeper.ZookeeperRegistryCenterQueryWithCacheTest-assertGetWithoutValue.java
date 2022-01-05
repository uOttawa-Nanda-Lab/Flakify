@Test public void assertGetWithoutValue(){
  assertNull(zkRegCenter.get("/test/null"));
}
