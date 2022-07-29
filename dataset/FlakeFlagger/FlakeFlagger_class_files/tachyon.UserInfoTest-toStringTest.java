package tachyon;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for tachyon.UserInfo
 */
public class UserInfoTest {
  private final int MIN_LEN = 1;
  private final int MAX_LEN = 1000;
  private final int DELTA = 50;

  @Test public void toStringTest(){UserInfo tUserInfo=new UserInfo(99);tUserInfo.addOwnBytes(2093);tUserInfo.addOwnBytes(-1029);Assert.assertEquals("UserInfo( USER_ID: 99, mOwnBytes: 1064, mLastHeartbeatMs: ",tUserInfo.toString().substring(0,58));}
}