@Test public void testOnBeanConditionWithXml(){
  this.context.register(XmlConfiguration.class,OnBeanNameConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.containsBean("bar"));
  assertEquals("bar",this.context.getBean("bar"));
}
