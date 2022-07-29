package org.jboss.as.test.integration.web.security.servlet3;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ServletSecurityRoleNamesAnnotationsTestCase {
/** 
 * Test with user "anil" who has the right password and the right role to access the servlet.
 * @throws Exception
 */
@Test public void testPasswordBasedSuccessfulAuth() throws Exception {
  makeCallSecured("anil","anil",200);
  makeCallWeaklySecured("anil","anil",200);
  makeCallHardSecured("anil","anil",403);
}

}