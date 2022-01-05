@Test public void testBindingDisabledCommands(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.setConversionService(new DefaultConversionService());
  binder.bind(new MutablePropertyValues(Collections.singletonMap("shell.disabled_commands","pattern1, pattern2")));
  assertFalse(binder.getBindingResult().hasErrors());
  assertEquals(2,props.getDisabledCommands().length);
  assertArrayEquals(new String[]{"pattern1","pattern2"},props.getDisabledCommands());
}
