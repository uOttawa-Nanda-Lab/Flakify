@Test public void testBindingKeyIgnored(){
  KeyAuthenticationProperties props=new KeyAuthenticationProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell.auth.key");
  binder.setConversionService(new DefaultConversionService());
  Map<String,String> map=new HashMap<String,String>();
  binder.bind(new MutablePropertyValues(map));
  assertFalse(binder.getBindingResult().hasErrors());
  Properties p=new Properties();
  props.applyToCrshShellConfig(p);
  assertNull(p.get("crash.auth.key.path"));
}
