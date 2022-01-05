@Test public void testBeanWrapperCreatesNewObjects() throws Exception {
  TargetWithNestedObject target=new TargetWithNestedObject();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  wrapper.setPropertyValue("nested.foo","bar");
  wrapper.getPropertyValue("nested");
  assertNotNull(wrapper.getPropertyValue("nested"));
}
