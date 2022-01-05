@Test public void testBindingCommandRefreshInterval(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.setConversionService(new DefaultConversionService());
  binder.bind(new MutablePropertyValues(Collections.singletonMap("shell.command_refresh_interval","1")));
  assertFalse(binder.getBindingResult().hasErrors());
  assertEquals(1,props.getCommandRefreshInterval());
}
