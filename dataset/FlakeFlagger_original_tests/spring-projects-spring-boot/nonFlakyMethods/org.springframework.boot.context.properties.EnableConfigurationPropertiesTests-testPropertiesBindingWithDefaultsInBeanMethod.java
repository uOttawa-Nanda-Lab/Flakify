@Test public void testPropertiesBindingWithDefaultsInBeanMethod(){
  this.context.register(DefaultConfiguration.class);
  this.context.refresh();
  String[] beanNames=this.context.getBeanNamesForType(TestProperties.class);
  assertEquals("Wrong beans: " + Arrays.asList(beanNames),1,beanNames.length);
  assertEquals("bar",this.context.getBean(TestProperties.class).name);
}
