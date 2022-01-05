@Test public void getActiveJSFVersionsTest(){
  List<String> versions=factory.getActiveJSFVersions();
  Assert.assertEquals(3,versions.size());
  Assert.assertTrue(versions.contains("main"));
  Assert.assertFalse(versions.contains("1.2"));
  Assert.assertTrue(versions.contains("myfaces"));
  Assert.assertTrue(versions.contains("myfaces2"));
}
