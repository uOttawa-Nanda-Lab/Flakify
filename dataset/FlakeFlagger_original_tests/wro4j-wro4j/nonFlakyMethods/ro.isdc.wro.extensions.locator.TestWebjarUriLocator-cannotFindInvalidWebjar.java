@Test(expected=IOException.class) public void cannotFindInvalidWebjar() throws Exception {
  assertNotEmpty(victim.locate("webjar:invalid.js"));
}
