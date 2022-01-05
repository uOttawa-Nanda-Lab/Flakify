@Test public void getUserIdTest(){
  for (int k=MIN_LEN; k < MAX_LEN; k+=66) {
    UserInfo tUserInfo=new UserInfo(k);
    Assert.assertEquals(k,tUserInfo.getUserId());
  }
}
