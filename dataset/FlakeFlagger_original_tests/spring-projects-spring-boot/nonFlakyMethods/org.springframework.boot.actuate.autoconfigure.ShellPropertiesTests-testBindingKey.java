@Test public void testBindingKey(){
  KeyAuthenticationProperties props=new KeyAuthenticationProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell.auth.key");
  binder.setConversionService(new DefaultConversionService());
  Map<String,String> map=new HashMap<String,String>();
  map.put("shell.auth.key.path","~/.ssh/test.pem");
  binder.bind(new MutablePropertyValues(map));
  assertFalse(binder.getBindingResult().hasErrors());
  Properties p=new Properties();
  props.applyToCrshShellConfig(p);
  assertEquals("~/.ssh/test.pem",p.get("crash.auth.key.path"));
}
