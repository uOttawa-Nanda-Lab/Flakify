@Test public void loadResourceName() throws Exception {
  BeanDefinitionLoader loader=new BeanDefinitionLoader(this.registry,"classpath:org/springframework/boot/sample-beans.xml");
  int loaded=loader.load();
  assertThat(loaded,equalTo(1));
  assertTrue(this.registry.containsBean("myXmlComponent"));
}
