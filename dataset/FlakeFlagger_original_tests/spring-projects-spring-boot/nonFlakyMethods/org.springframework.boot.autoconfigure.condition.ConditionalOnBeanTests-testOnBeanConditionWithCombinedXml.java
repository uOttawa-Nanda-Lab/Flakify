@Test public void testOnBeanConditionWithCombinedXml(){
  this.context.register(CombinedXmlConfiguration.class);
  this.context.refresh();
  assertFalse(this.context.containsBean("bar"));
}
