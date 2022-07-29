package com.ea.orbit.actors.test;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DeactivationTest {
@Test public void basicClientTest() throws ExecutionException, InterruptedException {
  OrbitStage stage=createStage();
  OrbitStage client=createClient();
  ISomeActor player=client.getReference(ISomeActor.class,"232");
  assertEquals("bla",player.sayHello("meh").get());
}

}