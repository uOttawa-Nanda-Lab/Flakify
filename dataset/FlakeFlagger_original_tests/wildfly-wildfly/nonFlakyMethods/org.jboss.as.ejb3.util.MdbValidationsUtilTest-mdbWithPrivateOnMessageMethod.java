@Test public void mdbWithPrivateOnMessageMethod() throws IOException, DeploymentUnitProcessingException {
  assertTrue(MdbValidationsUtil.assertMDBClassValidity(buildClassInfoForClass(InvalidMdbOnMessageCantBePrivate.class.getName())).contains(MdbValidityStatus.MDB_ON_MESSAGE_METHOD_CANT_BE_PRIVATE));
}
