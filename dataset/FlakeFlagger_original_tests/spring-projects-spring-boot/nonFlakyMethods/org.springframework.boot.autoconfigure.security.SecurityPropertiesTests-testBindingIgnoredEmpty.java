@Test public void testBindingIgnoredEmpty(){
  this.binder.bind(new MutablePropertyValues(Collections.singletonMap("security.ignored","")));
  assertFalse(this.binder.getBindingResult().hasErrors());
  assertEquals(0,this.security.getIgnored().size());
}
