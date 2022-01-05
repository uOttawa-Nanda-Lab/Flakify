@Test public void loadPackageAndClassDoesNotDoubleAdd() throws Exception {
  BeanDefinitionLoader loader=new BeanDefinitionLoader(this.registry,MyComponent.class.getPackage(),MyComponent.class);
  int loaded=loader.load();
  assertThat(loaded,equalTo(1));
  assertTrue(this.registry.containsBean("myComponent"));
}
