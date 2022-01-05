@Test public void loadGroovyName() throws Exception {
  BeanDefinitionLoader loader=new BeanDefinitionLoader(this.registry,"classpath:org/springframework/boot/sample-beans.groovy");
  int loaded=loader.load();
  assertThat(loaded,equalTo(1));
  assertTrue(this.registry.containsBean("myGroovyComponent"));
}
