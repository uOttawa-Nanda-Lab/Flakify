@Test public void spockAndJunitTester() throws Exception {
  String output=this.cli.test("spock.groovy","book_and_tests.groovy");
  assertThat(output,containsString("OK (2 tests)"));
}
