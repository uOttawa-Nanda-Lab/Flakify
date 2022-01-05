@Test public void testBindingSimple(){
  SimpleAuthenticationProperties props=new SimpleAuthenticationProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell.auth.simple");
  binder.setConversionService(new DefaultConversionService());
  Map<String,String> map=new HashMap<String,String>();
  map.put("shell.auth.simple.user.name","username123");
  map.put("shell.auth.simple.user.password","password123");
  binder.bind(new MutablePropertyValues(map));
  assertFalse(binder.getBindingResult().hasErrors());
  Properties p=new Properties();
  props.applyToCrshShellConfig(p);
  assertEquals("username123",p.get("crash.auth.simple.username"));
  assertEquals("password123",p.get("crash.auth.simple.password"));
}
