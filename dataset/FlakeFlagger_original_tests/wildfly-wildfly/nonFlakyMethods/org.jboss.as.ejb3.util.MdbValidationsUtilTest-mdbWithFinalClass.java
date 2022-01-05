@Test public void mdbWithFinalClass() throws IOException, DeploymentUnitProcessingException {
  assertTrue(MdbValidationsUtil.assertMDBClassValidity(buildClassInfoForClass(InvalidMdbFinalClass.class.getName())).contains(MdbValidityStatus.MDB_CLASS_CANNOT_BE_PRIVATE_ABSTRACT_OR_FINAL));
}
