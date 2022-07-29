package org.jboss.as.test.integration.web.security.servlet3;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ServletSecurityRoleNamesTestCase {
/** 
 * <p> Test with non-existent user "non-existent-user". </p> <p> Should be a HTTP/403 </p>
 * @throws Exception
 */
@Test public void testPasswordBasedUnsuccessfulAuthNonExistentUser() throws Exception {
  makeCallSecured("non-existent-user","non-existent-user",401);
  makeCallWeaklySecured("non-existent-user","non-existent-user",401);
  makeCallHardSecured("non-existent-user","non-existent-user",403);
}

}