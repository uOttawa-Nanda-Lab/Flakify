@Test public void testHolderClass(){
  ServiceClassHolder holder=ServiceClassHolder.getInstance();
  holder.pushServiceClass(ServiceHolderTest.class);
  Assert.assertEquals(ServiceHolderTest.class,holder.popServiceClass());
}
