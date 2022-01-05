@Test public void integrationAutoConfigEmbeddedTest() throws Exception {
  String output=this.cli.test("integration_auto.groovy");
  assertThat(output,containsString("OK (1 test)"));
}
