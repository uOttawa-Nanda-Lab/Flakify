@Test public void testNestedPlaceholder(){
  assertEquals("foo",SystemPropertyUtils.resolvePlaceholders("${bar:${spam:foo}}"));
}
