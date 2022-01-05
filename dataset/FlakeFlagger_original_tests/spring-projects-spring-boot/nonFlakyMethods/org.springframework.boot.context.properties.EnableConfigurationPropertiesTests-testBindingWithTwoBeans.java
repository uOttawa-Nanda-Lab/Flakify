@Test public void testBindingWithTwoBeans(){
  this.context.register(MoreConfiguration.class,TestConfiguration.class);
  this.context.refresh();
  assertEquals(1,this.context.getBeanNamesForType(TestProperties.class).length);
  assertEquals(1,this.context.getBeanNamesForType(MoreProperties.class).length);
}
