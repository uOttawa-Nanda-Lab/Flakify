@Test public void testAutoGrowWithFuzzyNameUnderscores() throws Exception {
  TargetWithNestedMap target=new TargetWithNestedMap();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  RelaxedDataBinder binder=new RelaxedDataBinder(target);
  String result=binder.normalizePath(wrapper,"nes_ted[foo][bar]");
  assertNotNull(wrapper.getPropertyValue("nested"));
  assertEquals("nested[foo][bar]",result);
  assertNotNull(wrapper.getPropertyValue("nested[foo][bar]"));
}
