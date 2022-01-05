@Test public void addOwnBytesTest(){
  UserInfo tUserInfo=new UserInfo(1);
  tUserInfo.addOwnBytes(7);
  tUserInfo.addOwnBytes(70);
  tUserInfo.addOwnBytes(700);
  tUserInfo.addOwnBytes(7000);
  Assert.assertEquals(7777,tUserInfo.getOwnBytes());
}
