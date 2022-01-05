@Test public void appInOneFileTestsInAnotherFile() throws Exception {
  String output=this.cli.test("book.groovy","test.groovy");
  assertThat(output,containsString("OK (1 test)"));
}
