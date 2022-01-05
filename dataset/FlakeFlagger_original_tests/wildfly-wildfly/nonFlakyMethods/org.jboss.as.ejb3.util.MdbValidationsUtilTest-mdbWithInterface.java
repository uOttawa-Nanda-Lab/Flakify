@Test public void mdbWithInterface() throws IOException, DeploymentUnitProcessingException {
  assertTrue(MdbValidationsUtil.assertMDBClassValidity(buildClassInfoForClass(InvalidMdbInterface.class.getName())).contains(MdbValidityStatus.MDB_CANNOT_BE_AN_INTERFACE));
}
