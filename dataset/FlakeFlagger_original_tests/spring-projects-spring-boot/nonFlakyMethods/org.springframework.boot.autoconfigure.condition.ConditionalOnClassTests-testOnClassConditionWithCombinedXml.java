@Test public void testOnClassConditionWithCombinedXml(){
  this.context.register(CombinedXmlConfiguration.class);
  this.context.refresh();
  assertTrue(this.context.containsBean("bar"));
  assertEquals("bar",this.context.getBean("bar"));
}
