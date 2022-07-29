package org.springframework.boot.actuate.metrics.repository.redis;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class RedisMultiMetricRepositoryTests {
@Test public void setAndGetMultiple(){
  this.repository.set("foo",Arrays.<Metric<?>>asList(new Metric<Number>("foo.val",12.3),new Metric<Number>("foo.bar",11.3)));
  assertEquals(2,Iterables.collection(this.repository.findAll("foo")).size());
}

}