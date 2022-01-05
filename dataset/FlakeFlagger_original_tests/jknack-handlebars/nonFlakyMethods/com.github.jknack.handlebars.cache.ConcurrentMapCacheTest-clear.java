@Test public void clear(){
  TemplateCache cache=new ConcurrentMapCache();
  Template template=createMock(Template.class);
  String key="key";
  cache.put(key,template);
  assertEquals(template,cache.get(key));
  cache.clear();
  assertNull(cache.get(key));
}
