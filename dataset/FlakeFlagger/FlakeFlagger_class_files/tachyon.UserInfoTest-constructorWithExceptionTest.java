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

  @Test(expected=RuntimeException.class) public void constructorWithExceptionTest(){for (int k=0;k >= -1000;k-=DELTA){UserInfo tUserInfo=new UserInfo(k);Assert.assertEquals(k,tUserInfo.getUserId());Assert.fail("UserId " + k + " should be invalid.");}}
}