@Test public void testBindingTelnet(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.setConversionService(new DefaultConversionService());
  Map<String,String> map=new HashMap<String,String>();
  map.put("shell.telnet.enabled","true");
  map.put("shell.telnet.port","2222");
  binder.bind(new MutablePropertyValues(map));
  assertFalse(binder.getBindingResult().hasErrors());
  Properties p=props.asCrshShellConfig();
  assertEquals("2222",p.get("crash.telnet.port"));
}
