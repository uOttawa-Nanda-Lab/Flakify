@Test public void testBindingSsh(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.setConversionService(new DefaultConversionService());
  Map<String,String> map=new HashMap<String,String>();
  map.put("shell.ssh.enabled","true");
  map.put("shell.ssh.port","2222");
  map.put("shell.ssh.key_path","~/.ssh/test.pem");
  binder.bind(new MutablePropertyValues(map));
  assertFalse(binder.getBindingResult().hasErrors());
  Properties p=props.asCrshShellConfig();
  assertEquals("2222",p.get("crash.ssh.port"));
  assertEquals("~/.ssh/test.pem",p.get("crash.ssh.keypath"));
}
