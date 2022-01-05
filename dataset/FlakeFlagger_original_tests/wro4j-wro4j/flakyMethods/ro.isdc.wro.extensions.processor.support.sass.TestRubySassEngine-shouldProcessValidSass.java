@Test public void shouldProcessValidSass() throws IOException {
  Assert.assertEquals("#element #child {\n  color: red; }\n",engine.process("#element { #child {color: red;}}"));
}
