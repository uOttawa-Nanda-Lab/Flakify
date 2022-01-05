@Test public void testStatusInvalid() throws Exception {
  try {
    EnglishReasonPhraseCatalog.INSTANCE.getReason(-1,null);
    Assert.fail("IllegalArgumentException must have been thrown (-1)");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    EnglishReasonPhraseCatalog.INSTANCE.getReason(99,null);
    Assert.fail("IllegalArgumentException must have been thrown (99)");
  }
 catch (  IllegalArgumentException expected) {
  }
  try {
    EnglishReasonPhraseCatalog.INSTANCE.getReason(600,null);
    Assert.fail("IllegalArgumentException must have been thrown (600)");
  }
 catch (  IllegalArgumentException expected) {
  }
}
