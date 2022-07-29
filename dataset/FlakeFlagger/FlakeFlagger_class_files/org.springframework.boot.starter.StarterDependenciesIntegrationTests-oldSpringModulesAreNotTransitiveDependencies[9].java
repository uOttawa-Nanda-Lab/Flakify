package org.springframework.boot.starter;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class StarterDependenciesIntegrationTests {
@Test public void oldSpringModulesAreNotTransitiveDependencies() throws IOException {
  runBuildForTask("checkSpring");
}

}