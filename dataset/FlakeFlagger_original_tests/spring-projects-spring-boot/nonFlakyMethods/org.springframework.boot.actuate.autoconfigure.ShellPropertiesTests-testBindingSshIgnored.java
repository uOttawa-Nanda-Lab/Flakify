@Test public void testBindingSshIgnored(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.setConversionService(new DefaultConversionService());
  Map<String,String> map=new HashMap<String,String>();
  map.put("shell.ssh.enabled","false");
  map.put("shell.ssh.port","2222");
  map.put("shell.ssh.key_path","~/.ssh/test.pem");
  binder.bind(new MutablePropertyValues(map));
  assertFalse(binder.getBindingResult().hasErrors());
  Properties p=props.asCrshShellConfig();
  assertNull(p.get("crash.ssh.port"));
  assertNull(p.get("crash.ssh.keypath"));
}
