@Test public void testBindingTelnetIgnored(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.setConversionService(new DefaultConversionService());
  Map<String,String> map=new HashMap<String,String>();
  map.put("shell.telnet.enabled","false");
  map.put("shell.telnet.port","2222");
  binder.bind(new MutablePropertyValues(map));
  assertFalse(binder.getBindingResult().hasErrors());
  Properties p=props.asCrshShellConfig();
  assertNull(p.get("crash.telnet.port"));
}
