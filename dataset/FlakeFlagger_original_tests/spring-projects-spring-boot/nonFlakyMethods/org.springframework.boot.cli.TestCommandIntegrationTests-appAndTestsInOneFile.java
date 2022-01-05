@Test public void appAndTestsInOneFile() throws Exception {
  String output=this.cli.test("book_and_tests.groovy");
  assertThat(output,containsString("OK (1 test)"));
}
