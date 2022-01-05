@Test public void putAndRemove(){
  TemplateCache cache=new ConcurrentMapCache();
  Template template=createMock(Template.class);
  String key="key";
  cache.put(key,template);
  assertEquals(template,cache.get(key));
  cache.evict(key);
  assertNull(cache.get(key));
}
