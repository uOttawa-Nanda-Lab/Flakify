@Test public void constructorTest(){
  for (int k=MIN_LEN; k <= MAX_LEN; k+=DELTA) {
    UserInfo tUserInfo=new UserInfo(k);
    Assert.assertEquals(k,tUserInfo.getUserId());
  }
}
