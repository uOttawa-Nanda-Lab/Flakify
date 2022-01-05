@Test public void testPropertiesBindingWithDefaultsInXml(){
  this.context.register(TestConfiguration.class,DefaultXmlConfiguration.class);
  this.context.refresh();
  String[] beanNames=this.context.getBeanNamesForType(TestProperties.class);
  assertEquals("Wrong beans: " + Arrays.asList(beanNames),1,beanNames.length);
  assertEquals("bar",this.context.getBean(TestProperties.class).name);
}
