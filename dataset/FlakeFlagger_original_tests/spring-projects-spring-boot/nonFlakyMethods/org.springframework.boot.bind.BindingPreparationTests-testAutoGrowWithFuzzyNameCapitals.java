@Test public void testAutoGrowWithFuzzyNameCapitals() throws Exception {
  TargetWithNestedMap target=new TargetWithNestedMap();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  RelaxedDataBinder binder=new RelaxedDataBinder(target);
  String result=binder.normalizePath(wrapper,"NESTED[foo][bar]");
  assertNotNull(wrapper.getPropertyValue("nested"));
  assertEquals("nested[foo][bar]",result);
  assertNotNull(wrapper.getPropertyValue("nested[foo][bar]"));
}
