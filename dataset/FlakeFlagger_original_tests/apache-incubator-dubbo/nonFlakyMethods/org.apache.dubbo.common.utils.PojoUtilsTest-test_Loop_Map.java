@Test public void test_Loop_Map() throws Exception {
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("k","v");
  map.put("m",map);
  assertSame(map,map.get("m"));
  System.out.println(map);
  Object generalize=PojoUtils.generalize(map);
  System.out.println(generalize);
  @SuppressWarnings("unchecked") Map<String,Object> ret=(Map<String,Object>)PojoUtils.realize(generalize,Map.class);
  System.out.println(ret);
  assertEquals("v",ret.get("k"));
  assertSame(ret,ret.get("m"));
}
