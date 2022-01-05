@Test public void startCloseTest(){
  try {
    boolean isClosing=channel.isClosing();
    Assert.assertFalse(isClosing);
    header.startClose();
    isClosing=channel.isClosing();
    Assert.assertTrue(isClosing);
  }
 catch (  Exception e) {
    e.printStackTrace();
  }
}
