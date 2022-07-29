package io.elasticjob.lite.spring.job;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class JobSpringNamespaceWithEventTraceRdbTest {
@Test public void assertSpringJobBean(){
  assertSimpleElasticJobBean();
  assertThroughputDataflowElasticJobBean();
}

}