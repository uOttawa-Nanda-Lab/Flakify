@Test public void spockTester() throws Exception {
  String output=this.cli.test("spock.groovy");
  assertThat(output,containsString("OK (1 test)"));
}
