@Test public void loadClassName() throws Exception {
  BeanDefinitionLoader loader=new BeanDefinitionLoader(this.registry,MyComponent.class.getName());
  int loaded=loader.load();
  assertThat(loaded,equalTo(1));
  assertTrue(this.registry.containsBean("myComponent"));
}
