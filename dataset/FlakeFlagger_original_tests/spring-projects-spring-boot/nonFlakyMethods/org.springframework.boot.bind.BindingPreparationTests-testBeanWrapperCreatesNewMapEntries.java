@Test public void testBeanWrapperCreatesNewMapEntries() throws Exception {
  TargetWithNestedMapOfBean target=new TargetWithNestedMapOfBean();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  wrapper.getPropertyValue("nested[foo]");
  wrapper.setPropertyValue("nested[foo].foo","bar");
  assertNotNull(wrapper.getPropertyValue("nested"));
  assertNotNull(wrapper.getPropertyValue("nested[foo]"));
}
