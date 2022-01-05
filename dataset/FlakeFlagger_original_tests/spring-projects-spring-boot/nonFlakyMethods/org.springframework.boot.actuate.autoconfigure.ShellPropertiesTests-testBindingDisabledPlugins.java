@Test public void testBindingDisabledPlugins(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.setConversionService(new DefaultConversionService());
  binder.bind(new MutablePropertyValues(Collections.singletonMap("shell.disabled_plugins","pattern1, pattern2")));
  assertFalse(binder.getBindingResult().hasErrors());
  assertEquals(2,props.getDisabledPlugins().length);
  assertArrayEquals(new String[]{"pattern1","pattern2"},props.getDisabledPlugins());
}
