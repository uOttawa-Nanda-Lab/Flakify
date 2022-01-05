@Test(expected=IllegalArgumentException.class) public void equalsTest(){
  Assert.assertEquals(header,new HeaderExchangeChannel(channel));
  header=new HeaderExchangeChannel(null);
  Assert.assertNotEquals(header,new HeaderExchangeChannel(channel));
}
