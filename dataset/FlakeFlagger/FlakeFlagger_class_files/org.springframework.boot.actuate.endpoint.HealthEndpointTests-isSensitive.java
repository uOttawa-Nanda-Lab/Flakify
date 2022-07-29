package org.springframework.boot.actuate.endpoint;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class HealthEndpointTests {
@Test public void isSensitive() throws Exception {
  assertThat(getEndpointBean().isSensitive(),equalTo(this.sensitive));
}

}