package org.springframework.boot.actuate.metrics.repository.redis;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class RedisMultiMetricRepositoryTests {
@Test public void increment(){
  this.repository.increment("foo",new Delta<Number>("foo.bar",1));
  this.repository.increment("foo",new Delta<Number>("foo.bar",2));
  this.repository.increment("foo",new Delta<Number>("foo.spam",1));
  Metric<?> bar=null;
  Set<String> names=new HashSet<String>();
  for (  Metric<?> metric : this.repository.findAll("foo")) {
    names.add(metric.getName());
    if (metric.getName().equals("foo.bar")) {
      bar=metric;
    }
  }
  assertEquals(2,names.size());
  assertTrue("Wrong names: " + names,names.contains("foo.bar"));
  assertEquals(3d,bar.getValue());
}

}