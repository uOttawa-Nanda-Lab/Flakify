@Test public void closeTest(){
  Assert.assertFalse(channel.isClosed());
  header.close();
  Assert.assertTrue(channel.isClosed());
}
