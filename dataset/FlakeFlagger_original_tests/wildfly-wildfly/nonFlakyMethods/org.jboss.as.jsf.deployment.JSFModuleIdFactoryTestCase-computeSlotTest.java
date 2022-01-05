@Test public void computeSlotTest(){
  Assert.assertEquals("main",factory.computeSlot("main"));
  Assert.assertEquals("main",factory.computeSlot(null));
  Assert.assertEquals("main",factory.computeSlot(JsfVersionMarker.JSF_2_0));
  Assert.assertEquals("myfaces2",factory.computeSlot("myfaces2"));
}
