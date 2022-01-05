@Test public void testRoles(){
  this.binder.bind(new MutablePropertyValues(Collections.singletonMap("security.user.role","USER,ADMIN")));
  assertFalse(this.binder.getBindingResult().hasErrors());
  assertEquals("[USER, ADMIN]",this.security.getUser().getRole().toString());
}
