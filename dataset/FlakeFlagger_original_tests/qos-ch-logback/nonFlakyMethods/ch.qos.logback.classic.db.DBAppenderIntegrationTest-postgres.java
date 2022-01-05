@Test public void postgres() throws Exception {
  if (!isConformingHostAndJDK16OrHigher(POSTGRES_CONFORMING_HOST_LIST)) {
    return;
  }
  System.out.println("running postgres() test");
  doTest("src/test/input/integration/db/postgresql-with-driver.xml");
}
