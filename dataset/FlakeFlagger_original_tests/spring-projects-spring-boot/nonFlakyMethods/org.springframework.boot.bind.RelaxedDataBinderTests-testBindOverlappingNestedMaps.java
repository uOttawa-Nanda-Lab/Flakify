@SuppressWarnings("unchecked") @Test public void testBindOverlappingNestedMaps() throws Exception {
  Map<String,Object> target=new LinkedHashMap<String,Object>();
  BindingResult result=bind(target,"a.b.c.d: abc\na.b.c1.d1: efg");
  assertEquals(0,result.getErrorCount());
  Map<String,Object> a=(Map<String,Object>)target.get("a");
  Map<String,Object> b=(Map<String,Object>)a.get("b");
  Map<String,Object> c=(Map<String,Object>)b.get("c");
  assertEquals("abc",c.get("d"));
  Map<String,Object> c1=(Map<String,Object>)b.get("c1");
  assertEquals("efg",c1.get("d1"));
}
