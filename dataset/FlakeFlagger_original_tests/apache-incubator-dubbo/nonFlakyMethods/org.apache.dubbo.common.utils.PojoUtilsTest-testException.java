@Test public void testException() throws Exception {
  Map map=new HashMap();
  map.put("message","dubbo exception");
  Object o=PojoUtils.realize(map,RuntimeException.class);
  assertEquals(((Throwable)o).getMessage(),"dubbo exception");
}
