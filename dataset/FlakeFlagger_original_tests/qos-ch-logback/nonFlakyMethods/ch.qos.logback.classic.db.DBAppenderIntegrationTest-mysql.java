@Test public void mysql() throws Exception {
  if (!isConformingHostAndJDK16OrHigher(MYSQL_CONFORMING_HOST_LIST)) {
    return;
  }
  doTest("src/test/input/integration/db/mysql-with-driver.xml");
}
