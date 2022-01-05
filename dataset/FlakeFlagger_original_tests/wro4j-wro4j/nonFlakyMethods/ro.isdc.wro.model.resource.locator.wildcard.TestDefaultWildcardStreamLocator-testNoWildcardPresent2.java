@Test public void testNoWildcardPresent2(){
  Assert.assertFalse(locator.hasWildcard("test/resource[a].css"));
}
