@Test public void singleMapLookup(){
  Map<String,Object> model=new HashMap<String,Object>();
  model.put("simple","value");
  Context context=Context.newContext(model);
  assertNotNull(context);
  assertEquals("value",context.get("simple"));
}
