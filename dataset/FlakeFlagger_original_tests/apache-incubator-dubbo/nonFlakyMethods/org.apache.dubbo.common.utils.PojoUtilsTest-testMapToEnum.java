@Test public void testMapToEnum() throws Exception {
  Map map=new HashMap();
  map.put("name","MONDAY");
  Object o=PojoUtils.realize(map,Day.class);
  assertEquals(o,Day.MONDAY);
}
