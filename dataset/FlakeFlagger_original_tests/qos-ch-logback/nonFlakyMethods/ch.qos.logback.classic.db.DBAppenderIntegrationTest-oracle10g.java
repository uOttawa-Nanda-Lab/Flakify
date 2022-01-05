@Test public void oracle10g() throws Exception {
  if (!isConformingHostAndJDK16OrHigher(ORACLE_CONFORMING_HOST_LIST)) {
    return;
  }
  doTest("src/test/input/integration/db/oracle10g-with-driver.xml");
}
