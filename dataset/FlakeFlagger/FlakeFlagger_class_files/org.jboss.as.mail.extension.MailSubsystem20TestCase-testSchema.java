package org.jboss.as.mail.extension;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class MailSubsystem20TestCase {
@Override public void testSchema() throws Exception {
  if (getSubsystemXsdPath() != null) {
    super.testSchema();
  }
}

}