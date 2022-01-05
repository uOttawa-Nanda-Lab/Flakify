@Test public void testDefaultValue(){
  assertEquals("foo",SystemPropertyUtils.resolvePlaceholders("${bar:foo}"));
}
