@Test public void testBeanWrapperLists() throws Exception {
  TargetWithNestedMapOfListOfString target=new TargetWithNestedMapOfListOfString();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  TypeDescriptor descriptor=wrapper.getPropertyTypeDescriptor("nested");
  assertTrue(descriptor.isMap());
  wrapper.getPropertyValue("nested[foo]");
  assertNotNull(wrapper.getPropertyValue("nested"));
  wrapper.setPropertyValue("nested[foo][0]","bar");
  wrapper.getPropertyValue("nested[foo][0]");
  assertNotNull(wrapper.getPropertyValue("nested[foo]"));
}
