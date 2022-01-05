@Test public void eachKeyWithString() throws IOException {
  shouldCompileTo("{{#each this}}{{@key}} {{/each}}","String","empty bytes ");
}
