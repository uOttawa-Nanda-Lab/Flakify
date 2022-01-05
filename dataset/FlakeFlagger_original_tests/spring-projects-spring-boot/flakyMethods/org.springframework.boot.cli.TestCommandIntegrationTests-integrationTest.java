@Test public void integrationTest() throws Exception {
  String output=this.cli.test("integration.groovy");
  assertThat(output,containsString("OK (1 test)"));
}
