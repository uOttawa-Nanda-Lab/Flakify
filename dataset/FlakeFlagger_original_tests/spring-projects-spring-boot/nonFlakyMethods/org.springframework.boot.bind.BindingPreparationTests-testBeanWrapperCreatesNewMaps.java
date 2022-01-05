@Test public void testBeanWrapperCreatesNewMaps() throws Exception {
  TargetWithNestedMap target=new TargetWithNestedMap();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  wrapper.getPropertyValue("nested[foo]");
  assertNotNull(wrapper.getPropertyValue("nested"));
}
