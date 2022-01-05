@Test public void testObject(){
  RpcContext context=RpcContext.getContext();
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("_11","1111");
  map.put("_22","2222");
  map.put(".33","3333");
  map.forEach(context::set);
  Assert.assertEquals(map,context.get());
  Assert.assertEquals("1111",context.get("_11"));
  context.set("_11","11.11");
  Assert.assertEquals("11.11",context.get("_11"));
  context.set(null,"22222");
  context.set("_22",null);
  Assert.assertEquals("22222",context.get(null));
  Assert.assertNull(context.get("_22"));
  Assert.assertNull(context.get("_33"));
  Assert.assertEquals("3333",context.get(".33"));
  map.keySet().forEach(context::remove);
  Assert.assertNull(context.get("_11"));
}
