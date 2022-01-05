@Test public void testBindingIgnoredDisable(){
  this.binder.bind(new MutablePropertyValues(Collections.singletonMap("security.ignored","none")));
  assertFalse(this.binder.getBindingResult().hasErrors());
  assertEquals(1,this.security.getIgnored().size());
}
