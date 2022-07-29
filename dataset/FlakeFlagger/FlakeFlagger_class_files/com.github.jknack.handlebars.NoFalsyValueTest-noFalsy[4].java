package com.github.jknack.handlebars;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class NoFalsyValueTest {
@Test public void noFalsy(){
  assertEquals(false,Handlebars.Utils.isEmpty(value));
}

}