@Test public void empty() throws Exception {
  String output=this.cli.test("empty.groovy");
  assertThat(output,containsString("No tests found"));
}
