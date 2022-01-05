@Test public void testPutGet() throws Exception {
  assertNull(dataStore.get("xxx","yyy"));
  dataStore.put("name","key","1");
  assertEquals("1",dataStore.get("name","key"));
  assertNull(dataStore.get("xxx","yyy"));
}
