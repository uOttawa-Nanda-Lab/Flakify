@Test public void multipleValueResolvers(){
  Map<String,Object> map=new HashMap<String,Object>();
  map.put("base","a");
  map.put("child","b");
  Context context=Context.newBuilder(new Base("a","b")).combine("map",map).resolver(MapValueResolver.INSTANCE,JavaBeanValueResolver.INSTANCE,MethodValueResolver.INSTANCE,FieldValueResolver.INSTANCE).build();
  assertNotNull(context);
  assertEquals("a",context.get("base"));
  assertEquals("b",context.get("child"));
  assertEquals("a",context.get("baseProperty"));
  assertEquals("b",context.get("childProperty"));
  assertEquals("a",context.get("getBaseProperty"));
  assertEquals("b",context.get("getChildProperty"));
  assertEquals("a",context.get("map.base"));
  assertEquals("b",context.get("map.child"));
}
