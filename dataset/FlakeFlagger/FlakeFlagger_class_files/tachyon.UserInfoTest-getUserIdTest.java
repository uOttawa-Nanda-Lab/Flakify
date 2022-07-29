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

  @Test public void getUserIdTest(){for (int k=MIN_LEN;k < MAX_LEN;k+=66){UserInfo tUserInfo=new UserInfo(k);Assert.assertEquals(k,tUserInfo.getUserId());}}
}