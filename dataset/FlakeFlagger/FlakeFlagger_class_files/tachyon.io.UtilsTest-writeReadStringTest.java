package tachyon.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import tachyon.TestUtils;

public class UtilsTest {

  @Test public void writeReadStringTest() throws IOException{ByteArrayOutputStream os=new ByteArrayOutputStream();DataOutputStream dos=new DataOutputStream(os);List<String> strings=new ArrayList<String>();strings.add("");strings.add(null);strings.add("abc xyz");strings.add("123 789");strings.add("!@#$%^&*()_+}{\":?><");for (int k=0;k < strings.size();k++){Utils.writeString(strings.get(k),dos);}DataInputStream dis=new DataInputStream(new ByteArrayInputStream(os.toByteArray()));for (int k=0;k < strings.size();k++){Assert.assertEquals(strings.get(k),Utils.readString(dis));}dos.close();dis.close();}
}