package org.springframework.boot.actuate.endpoint;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ConfigurationPropertiesReportEndpointTests {
@Test public void getId() throws Exception {
  assertThat(getEndpointBean().getId(),equalTo(this.id));
}

}