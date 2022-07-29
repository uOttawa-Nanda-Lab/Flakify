package tachyon.command;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import tachyon.Constants;
import tachyon.LocalTachyonCluster;
import tachyon.TestUtils;
import tachyon.client.InStream;
import tachyon.client.ReadType;
import tachyon.client.TachyonFS;
import tachyon.client.TachyonFile;
import tachyon.client.WriteType;
import tachyon.thrift.ClientBlockInfo;

/**
 * Unit tests on TFsShell.
 */
public class TFsShellTest {
  private final int mSizeBytes = Constants.MB * 10;
  private LocalTachyonCluster mLocalTachyonCluster = null;
  private TachyonFS mTfs = null;
  private TFsShell mFsShell = null;
  private ByteArrayOutputStream mOutput = null;
  private PrintStream mNewOutput = null;
  private PrintStream mOldOutput = null;

  private String getCommandOutput(String[] command) {
    String cmd = command[0];
    if (command.length == 2) {
      if (cmd.equals("ls")) {
        // Not sure how to handle this one.
        return null; 
      } else if (cmd.equals("mkdir")) {
        return "Successfully created directory " + command[1] + "\n";
      } else if (cmd.equals("rm")) {
        return command[1] + " has been removed" + "\n";
      }
    } else if (command.length == 3) {
      if (cmd.equals("mv")) {
        return "Renamed " + command[1] + " to " + command[2] + "\n";
      } else if (cmd.equals("copyFromLocal")) {
        return "Copied " + command[1] + " to " + command[2] + "\n";
      } else if (cmd.equals("copyToLocal")) {
        return "Copied " + command[1] + " to " + command[2] + "\n";
      }
    } else if (command.length > 3) {
      if (cmd.equals("location")) {
        StringBuilder ret = new StringBuilder();
        ret.append(command[1] + " with file id " + command[2] + " are on nodes: \n");
        for (int i = 3; i < command.length; i ++) {
          ret.append(command[i] + "\n");
        }
        return ret.toString();
      }else if (cmd.equals("fileinfo")) {
        StringBuilder ret = new StringBuilder();
        ret.append(command[1] + " with file id " + command[2] + " have following blocks: \n");
        for (int i = 3; i < command.length; i ++) {
          ret.append(command[i] + "\n");
        }
        return ret.toString();
      }
    }
    return null;
  }


  @Before
  public final void before() throws IOException {
    System.setProperty("tachyon.user.quota.unit.bytes", "1000");
    mLocalTachyonCluster = new LocalTachyonCluster(mSizeBytes);
    mLocalTachyonCluster.start();
    mTfs = mLocalTachyonCluster.getClient();
    mFsShell = new TFsShell();
    mOutput = new ByteArrayOutputStream();
    mNewOutput = new PrintStream(mOutput);
    mOldOutput = System.out;
    System.setOut(mNewOutput);
  }

  @Test public void rmTest() throws IOException{StringBuilder toCompare=new StringBuilder();mFsShell.mkdir(new String[]{"mkdir","/testFolder1/testFolder2/testFile2"});toCompare.append(getCommandOutput(new String[]{"mkdir","/testFolder1/testFolder2/testFile2"}));Assert.assertNotNull(mTfs.getFile("/testFolder1"));Assert.assertNotNull(mTfs.getFile("/testFolder1/testFolder2"));Assert.assertNotNull(mTfs.getFile("/testFolder1/testFolder2/testFile2"));mFsShell.rm(new String[]{"rm","/testFolder1/testFolder2/testFile2"});toCompare.append(getCommandOutput(new String[]{"rm","/testFolder1/testFolder2/testFile2"}));Assert.assertEquals(toCompare.toString(),mOutput.toString());Assert.assertNotNull(mTfs.getFile("/testFolder1"));Assert.assertNotNull(mTfs.getFile("/testFolder1/testFolder2"));Assert.assertNull(mTfs.getFile("/testFolder1/testFolder2/testFile2"));mFsShell.rm(new String[]{"rm","/testFolder1"});toCompare.append(getCommandOutput(new String[]{"rm","/testFolder1"}));Assert.assertEquals(toCompare.toString(),mOutput.toString());Assert.assertNull(mTfs.getFile("/testFolder1"));Assert.assertNull(mTfs.getFile("/testFolder1/testFolder2"));Assert.assertNull(mTfs.getFile("/testFolder1/testFolder2/testFile2"));}
}
