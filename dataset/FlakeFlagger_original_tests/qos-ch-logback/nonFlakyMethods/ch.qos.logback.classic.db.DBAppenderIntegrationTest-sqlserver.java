@Test public void sqlserver() throws Exception {
  if (!isConformingHostAndJDK16OrHigher()) {
    return;
  }
  doTest("src/test/input/integration/db/sqlserver-with-driver.xml");
}
