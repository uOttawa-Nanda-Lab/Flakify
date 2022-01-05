@Test public void loadPackage() throws Exception {
  BeanDefinitionLoader loader=new BeanDefinitionLoader(this.registry,MyComponent.class.getPackage());
  int loaded=loader.load();
  assertThat(loaded,equalTo(1));
  assertTrue(this.registry.containsBean("myComponent"));
}
