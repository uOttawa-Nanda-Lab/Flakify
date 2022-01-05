@Test public void verifyFailures() throws Exception {
  String output=this.cli.test("failures.groovy");
  assertThat(output,containsString("Tests run: 5,  Failures: 3"));
}
