package com.google.zxing.datamatrix;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class DataMatrixBlackBox2TestCase {
@Test public void testBlackBox() throws IOException {
  testBlackBoxCountingResults(true);
}

}