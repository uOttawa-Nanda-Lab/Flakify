@Test public void testAutoGrowListOfMaps() throws Exception {
  TargetWithNestedListOfMaps target=new TargetWithNestedListOfMaps();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  RelaxedDataBinder binder=new RelaxedDataBinder(target);
  binder.normalizePath(wrapper,"nested[0][foo]");
  assertNotNull(wrapper.getPropertyValue("nested"));
  assertNotNull(wrapper.getPropertyValue("nested[0]"));
}
