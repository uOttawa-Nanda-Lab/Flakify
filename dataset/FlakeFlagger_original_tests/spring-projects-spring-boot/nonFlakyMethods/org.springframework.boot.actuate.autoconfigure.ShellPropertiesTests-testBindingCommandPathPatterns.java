@Test public void testBindingCommandPathPatterns(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.setConversionService(new DefaultConversionService());
  binder.bind(new MutablePropertyValues(Collections.singletonMap("shell.command_path_patterns","pattern1, pattern2")));
  assertFalse(binder.getBindingResult().hasErrors());
  assertEquals(2,props.getCommandPathPatterns().length);
  Assert.assertArrayEquals(new String[]{"pattern1","pattern2"},props.getCommandPathPatterns());
}
