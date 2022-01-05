@Test public void nestedMapLookup(){
  Map<String,Object> model=new HashMap<String,Object>();
  Map<String,Object> nested=new HashMap<String,Object>();
  model.put("nested",nested);
  nested.put("simple","value");
  Context context=Context.newContext(model);
  assertNotNull(context);
  assertEquals("value",context.get("nested.simple"));
}
