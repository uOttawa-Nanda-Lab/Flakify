@Test public void testRemove() throws Exception {
  dataStore.remove("xxx","yyy");
  dataStore.put("name","key","1");
  dataStore.remove("name","key");
  assertNull(dataStore.get("name","key"));
}
