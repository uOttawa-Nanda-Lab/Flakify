@Test public void shouldSetMinimizeFlag(){
  final Resource resource=Resource.create("resource.js");
  resource.setMinimize(true);
  Assert.assertTrue(resource.isMinimize());
  resource.setMinimize(false);
  Assert.assertFalse(resource.isMinimize());
}
