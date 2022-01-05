@Test public void mdbWithStaticOnMessageMethod() throws IOException, DeploymentUnitProcessingException {
  assertTrue(MdbValidationsUtil.assertMDBClassValidity(buildClassInfoForClass(InvalidMdbOnMessageCantBeStatic.class.getName())).contains(MdbValidityStatus.MDB_ON_MESSAGE_METHOD_CANT_BE_STATIC));
}
