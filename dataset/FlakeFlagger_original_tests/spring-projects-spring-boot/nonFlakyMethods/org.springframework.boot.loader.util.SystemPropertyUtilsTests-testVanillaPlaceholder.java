@Test public void testVanillaPlaceholder(){
  assertEquals("bar",SystemPropertyUtils.resolvePlaceholders("${foo}"));
}
