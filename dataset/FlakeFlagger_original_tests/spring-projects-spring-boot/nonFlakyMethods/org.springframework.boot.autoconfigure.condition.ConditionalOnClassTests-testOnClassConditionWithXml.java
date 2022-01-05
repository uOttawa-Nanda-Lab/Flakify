@Test public void testOnClassConditionWithXml(){
  this.context.register(BasicConfiguration.class,XmlConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.containsBean("bar"));
  assertEquals("bar",this.context.getBean("bar"));
}
