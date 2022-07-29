package org.assertj.core.api;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class BDDAssertions_then_Test {
@Test public void then_byte(){
  then((byte)7).isEqualTo((byte)0x07);
}

}