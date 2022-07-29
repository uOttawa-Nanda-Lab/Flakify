package org.jboss.as.test.integration.web.security.servlet3;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ServletSecurityRoleNamesAnnotationsTestCase {
/** 
 * <p> Test with user "marcus" who has the right password but does not have the right role. </p> <p> Should be a HTTP/403 </p>
 * @throws Exception
 */
@Test public void testPasswordBasedUnsuccessfulAuth() throws Exception {
  makeCallSecured("marcus","marcus",403);
  makeCallWeaklySecured("marcus","marcus",200);
  makeCallHardSecured("marcus","marcus",403);
}

}