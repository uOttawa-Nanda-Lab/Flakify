package com.github.jknack.handlebars;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class FalsyValueTest {
@Test public void falsy(){
  assertEquals(true,Handlebars.Utils.isEmpty(value));
}

}