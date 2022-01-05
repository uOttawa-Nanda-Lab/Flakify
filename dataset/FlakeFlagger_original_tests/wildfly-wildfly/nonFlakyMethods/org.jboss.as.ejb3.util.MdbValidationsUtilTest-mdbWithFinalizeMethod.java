@Test public void mdbWithFinalizeMethod() throws IOException, DeploymentUnitProcessingException {
  assertTrue(MdbValidationsUtil.assertMDBClassValidity(buildClassInfoForClass(InvalidMdbWithFinalizeMethod.class.getName())).contains(MdbValidityStatus.MDB_SHOULD_NOT_HAVE_FINALIZE_METHOD));
}
