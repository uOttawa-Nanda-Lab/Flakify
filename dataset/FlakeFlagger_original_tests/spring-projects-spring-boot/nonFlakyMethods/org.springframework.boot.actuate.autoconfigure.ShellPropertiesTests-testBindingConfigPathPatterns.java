@Test public void testBindingConfigPathPatterns(){
  ShellProperties props=new ShellProperties();
  RelaxedDataBinder binder=new RelaxedDataBinder(props,"shell");
  binder.setConversionService(new DefaultConversionService());
  binder.bind(new MutablePropertyValues(Collections.singletonMap("shell.config_path_patterns","pattern1, pattern2")));
  assertFalse(binder.getBindingResult().hasErrors());
  assertEquals(2,props.getConfigPathPatterns().length);
  Assert.assertArrayEquals(new String[]{"pattern1","pattern2"},props.getConfigPathPatterns());
}
