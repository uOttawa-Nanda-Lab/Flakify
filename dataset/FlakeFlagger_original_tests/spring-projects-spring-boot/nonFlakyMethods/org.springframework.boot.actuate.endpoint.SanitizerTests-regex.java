@Test public void regex() throws Exception {
  this.sanitizer.setKeysToSanitize(".*lock.*");
  assertEquals(this.sanitizer.sanitize("verylOCkish","secret"),"******");
  assertEquals(this.sanitizer.sanitize("veryokish","secret"),"secret");
}
