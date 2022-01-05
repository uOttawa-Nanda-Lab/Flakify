@Test public void noTests() throws Throwable {
  String output=this.cli.test("book.groovy");
  assertThat(output,containsString("No tests found"));
}
