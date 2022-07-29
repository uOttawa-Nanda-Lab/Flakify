package org.springframework.boot.actuate.metrics.repository.redis;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class RedisMultiMetricRepositoryTests {
@Test public void setAndGet(){
  this.repository.set("foo",Arrays.<Metric<?>>asList(new Metric<Number>("foo.bar",12.3)));
  this.repository.set("foo",Arrays.<Metric<?>>asList(new Metric<Number>("foo.bar",15.3)));
  assertEquals(15.3,Iterables.collection(this.repository.findAll("foo")).iterator().next().getValue());
}

}