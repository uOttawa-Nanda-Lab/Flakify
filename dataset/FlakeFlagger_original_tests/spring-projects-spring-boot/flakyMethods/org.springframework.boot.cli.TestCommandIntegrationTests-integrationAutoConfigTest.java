@Test public void integrationAutoConfigTest() throws Exception {
  String output=this.cli.test("integration_auto_test.groovy","reactor.groovy");
  assertThat(output,containsString("OK (1 test)"));
}
