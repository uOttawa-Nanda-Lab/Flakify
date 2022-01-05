@Test(expected=RuntimeException.class) public void constructorWithExceptionTest(){
  for (int k=0; k >= -1000; k-=DELTA) {
    UserInfo tUserInfo=new UserInfo(k);
    Assert.assertEquals(k,tUserInfo.getUserId());
    Assert.fail("UserId " + k + " should be invalid.");
  }
}
