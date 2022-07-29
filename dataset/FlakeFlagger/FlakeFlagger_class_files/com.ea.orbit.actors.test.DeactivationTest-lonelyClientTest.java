package com.ea.orbit.actors.test;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DeactivationTest {
@Test public void lonelyClientTest() throws ExecutionException, InterruptedException {
  OrbitStage client=createClient();
  ISomeActor player=client.getReference(ISomeActor.class,"232");
  client.getHosting().setTimeToWaitForServersMillis(100);
  expectException(() -> player.sayHello("meh"));
}

}