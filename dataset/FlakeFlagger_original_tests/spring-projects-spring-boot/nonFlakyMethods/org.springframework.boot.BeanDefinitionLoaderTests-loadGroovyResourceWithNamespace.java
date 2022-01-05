@Test public void loadGroovyResourceWithNamespace() throws Exception {
  ClassPathResource resource=new ClassPathResource("sample-namespace.groovy",getClass());
  BeanDefinitionLoader loader=new BeanDefinitionLoader(this.registry,resource);
  int loaded=loader.load();
  assertThat(loaded,equalTo(1));
  assertTrue(this.registry.containsBean("myGroovyComponent"));
}
