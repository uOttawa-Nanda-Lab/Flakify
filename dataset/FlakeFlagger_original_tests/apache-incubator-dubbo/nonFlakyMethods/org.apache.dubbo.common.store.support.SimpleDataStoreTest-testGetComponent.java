@Test public void testGetComponent() throws Exception {
  Map<String,Object> map=dataStore.get("component");
  assertTrue(map != null && map.isEmpty());
  dataStore.put("component","key","value");
  map=dataStore.get("component");
  assertTrue(map != null && map.size() == 1);
  dataStore.remove("component","key");
  assertNotEquals(map,dataStore.get("component"));
}
