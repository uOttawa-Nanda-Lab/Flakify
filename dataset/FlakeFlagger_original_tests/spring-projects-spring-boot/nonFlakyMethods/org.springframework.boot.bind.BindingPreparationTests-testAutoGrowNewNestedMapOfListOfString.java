@Test public void testAutoGrowNewNestedMapOfListOfString() throws Exception {
  TargetWithNestedMapOfListOfString target=new TargetWithNestedMapOfListOfString();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  RelaxedDataBinder binder=new RelaxedDataBinder(target);
  binder.normalizePath(wrapper,"nested[foo][0]");
  assertNotNull(wrapper.getPropertyValue("nested"));
  assertNotNull(wrapper.getPropertyValue("nested[foo]"));
}
