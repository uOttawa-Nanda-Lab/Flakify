@Test public void testBindingIgnoredMultiValued(){
  this.binder.bind(new MutablePropertyValues(Collections.singletonMap("security.ignored","/css/**,/images/**")));
  assertFalse(this.binder.getBindingResult().hasErrors());
  assertEquals(2,this.security.getIgnored().size());
}
