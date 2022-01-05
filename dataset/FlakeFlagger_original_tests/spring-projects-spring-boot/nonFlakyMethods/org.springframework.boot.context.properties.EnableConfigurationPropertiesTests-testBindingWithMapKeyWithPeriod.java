@Test public void testBindingWithMapKeyWithPeriod(){
  this.context.register(ResourceBindingPropertiesWithMap.class);
  this.context.refresh();
  ResourceBindingPropertiesWithMap bean=this.context.getBean(ResourceBindingPropertiesWithMap.class);
  assertEquals("value3",bean.mymap.get("key3"));
  assertEquals("value12",bean.mymap.get("key1.key2"));
}
