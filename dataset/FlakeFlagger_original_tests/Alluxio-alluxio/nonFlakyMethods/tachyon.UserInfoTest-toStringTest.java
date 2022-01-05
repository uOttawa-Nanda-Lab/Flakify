@Test public void toStringTest(){
  UserInfo tUserInfo=new UserInfo(99);
  tUserInfo.addOwnBytes(2093);
  tUserInfo.addOwnBytes(-1029);
  Assert.assertEquals("UserInfo( USER_ID: 99, mOwnBytes: 1064, mLastHeartbeatMs: ",tUserInfo.toString().substring(0,58));
}
