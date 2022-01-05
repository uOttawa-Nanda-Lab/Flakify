@Test public void validSlotTest(){
  Assert.assertTrue(factory.isValidJSFSlot("main"));
  Assert.assertFalse(factory.isValidJSFSlot("1.2"));
  Assert.assertTrue(factory.isValidJSFSlot("myfaces"));
  Assert.assertTrue(factory.isValidJSFSlot("myfaces2"));
  Assert.assertTrue(factory.isValidJSFSlot(JsfVersionMarker.JSF_2_0));
  Assert.assertFalse(factory.isValidJSFSlot(JsfVersionMarker.WAR_BUNDLES_JSF_IMPL));
  Assert.assertFalse(factory.isValidJSFSlot("bogus"));
  Assert.assertFalse(factory.isValidJSFSlot("bogus2"));
}
