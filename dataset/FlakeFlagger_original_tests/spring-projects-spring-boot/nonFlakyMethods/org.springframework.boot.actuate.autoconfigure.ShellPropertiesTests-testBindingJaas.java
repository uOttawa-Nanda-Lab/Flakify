@Test public void testBindingJaas(){
  JaasAuthenticationProperties props=new JaasAuthenticationProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell.auth.jaas");
  binder.setConversionService(new DefaultConversionService());
  Map<String,String> map=new HashMap<String,String>();
  map.put("shell.auth.jaas.domain","my-test-domain");
  binder.bind(new MutablePropertyValues(map));
  assertFalse(binder.getBindingResult().hasErrors());
  Properties p=new Properties();
  props.applyToCrshShellConfig(p);
  assertEquals("my-test-domain",p.get("crash.auth.jaas.domain"));
}
