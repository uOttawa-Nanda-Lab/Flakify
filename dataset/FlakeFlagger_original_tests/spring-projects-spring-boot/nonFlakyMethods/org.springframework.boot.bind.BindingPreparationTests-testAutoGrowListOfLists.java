@Test public void testAutoGrowListOfLists() throws Exception {
  TargetWithNestedListOfLists target=new TargetWithNestedListOfLists();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  RelaxedDataBinder binder=new RelaxedDataBinder(target);
  binder.normalizePath(wrapper,"nested[0][1]");
  assertNotNull(wrapper.getPropertyValue("nested"));
  assertNotNull(wrapper.getPropertyValue("nested[0][1]"));
}
