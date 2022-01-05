@Test public void testBindingIgnoredSingleValued(){
  this.binder.bind(new MutablePropertyValues(Collections.singletonMap("security.ignored","/css/**")));
  assertFalse(this.binder.getBindingResult().hasErrors());
  assertEquals(1,this.security.getIgnored().size());
}
