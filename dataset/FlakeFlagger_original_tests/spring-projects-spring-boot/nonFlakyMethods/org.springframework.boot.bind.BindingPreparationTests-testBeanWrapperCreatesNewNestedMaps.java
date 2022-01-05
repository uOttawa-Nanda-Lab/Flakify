@Test public void testBeanWrapperCreatesNewNestedMaps() throws Exception {
  TargetWithNestedMap target=new TargetWithNestedMap();
  BeanWrapperImpl wrapper=new BeanWrapperImpl(target);
  wrapper.setAutoGrowNestedPaths(true);
  wrapper.getPropertyValue("nested[foo]");
  wrapper.setPropertyValue("nested[foo]",new LinkedHashMap<String,Object>());
  wrapper.setPropertyValue("nested[foo]",new ArrayList<Object>());
  wrapper.setPropertyValue("nested[foo][0]","bar");
  assertNotNull(wrapper.getPropertyValue("nested[foo][0]"));
}
