@Test public void testRole(){
  this.binder.bind(new MutablePropertyValues(Collections.singletonMap("security.user.role","ADMIN")));
  assertFalse(this.binder.getBindingResult().hasErrors());
  assertEquals("[ADMIN]",this.security.getUser().getRole().toString());
}
