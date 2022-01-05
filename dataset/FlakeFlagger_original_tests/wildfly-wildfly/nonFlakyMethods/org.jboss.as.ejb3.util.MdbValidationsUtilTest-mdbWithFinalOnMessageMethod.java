@Test public void mdbWithFinalOnMessageMethod() throws IOException, DeploymentUnitProcessingException {
  assertTrue(MdbValidationsUtil.assertMDBClassValidity(buildClassInfoForClass(InvalidMdbOnMessageCantBeFinal.class.getName())).contains(MdbValidityStatus.MDB_ON_MESSAGE_METHOD_CANT_BE_FINAL));
}
