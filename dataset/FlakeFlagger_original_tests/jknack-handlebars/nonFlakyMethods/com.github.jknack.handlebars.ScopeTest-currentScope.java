@Test public void currentScope() throws IOException {
  Object context=$("value","parent","child",$);
  shouldCompileTo("{{#child}}{{this.value}}{{/child}}",context,"");
  shouldCompileTo("{{#child}}{{value}}{{/child}}",context,"parent");
}
