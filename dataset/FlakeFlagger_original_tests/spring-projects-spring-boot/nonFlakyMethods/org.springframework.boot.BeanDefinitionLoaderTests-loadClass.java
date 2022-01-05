@Test public void loadClass() throws Exception {
  BeanDefinitionLoader loader=new BeanDefinitionLoader(this.registry,MyComponent.class);
  int loaded=loader.load();
  assertThat(loaded,equalTo(1));
  assertTrue(this.registry.containsBean("myComponent"));
}
