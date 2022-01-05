@Test public void shouldProcessValidCss() throws IOException {
  Assert.assertEquals("#element {\n  color: red; }\n",engine.process("#element {color: red;}"));
}
