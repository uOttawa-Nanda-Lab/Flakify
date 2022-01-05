@Test public void testBindingIgnoredMultiValuedList(){
  Map<String,String> map=new HashMap<String,String>();
  map.put("security.ignored[0]","/css/**");
  map.put("security.ignored[1]","/foo/**");
  this.binder.bind(new MutablePropertyValues(map));
  assertFalse(this.binder.getBindingResult().hasErrors());
  assertEquals(2,this.security.getIgnored().size());
  assertTrue(this.security.getIgnored().contains("/foo/**"));
}
