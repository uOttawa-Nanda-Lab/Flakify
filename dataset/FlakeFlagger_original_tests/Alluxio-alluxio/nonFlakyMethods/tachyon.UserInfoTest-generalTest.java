@Test public void generalTest(){
  for (int k=MIN_LEN; k < MAX_LEN; k++) {
    UserInfo tUserInfo=new UserInfo(k);
    tUserInfo.addOwnBytes(3222 * k);
    tUserInfo.addOwnBytes(-1111 * k);
    Assert.assertEquals(2111 * k,tUserInfo.getOwnBytes());
  }
}
