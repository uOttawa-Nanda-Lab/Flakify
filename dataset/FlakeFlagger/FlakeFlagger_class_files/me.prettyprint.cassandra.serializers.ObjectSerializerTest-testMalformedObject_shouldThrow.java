package me.prettyprint.cassandra.serializers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import me.prettyprint.hector.api.exceptions.HectorSerializationException;

import org.junit.Test;

/**
 *
 * @author Bozhidar Bozhanov
 *
 */
public class ObjectSerializerTest {

  @Test(expected=HectorSerializationException.class) public void testMalformedObject_shouldThrow(){ObjectSerializer ser=ObjectSerializer.get();ser.fromByteBuffer(ByteBuffer.wrap(new byte[]{1,2,3}));}

  private void test(Object object) {
    ObjectSerializer ser = ObjectSerializer.get();
    assertEquals(object, ser.fromByteBuffer(ser.toByteBuffer(object)));
  }


  @SuppressWarnings("serial")
  private static class SampleObject implements Serializable {
    private final String a = "test";
    private final List<Void> b = new ArrayList<Void>();
    private final Calendar c = Calendar.getInstance();
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + (a == null ? 0 : a.hashCode());
      result = prime * result + (b == null ? 0 : b.hashCode());
      result = prime * result + (c == null ? 0 : c.hashCode());
      return result;
    }
    @Override
    public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      }
      if (obj == null) {
        return false;
      }
      if (getClass() != obj.getClass()) {
        return false;
      }
      SampleObject other = (SampleObject) obj;
      if (a == null) {
        if (other.a != null) {
          return false;
        }
      } else if (!a.equals(other.a)) {
        return false;
      }
      if (b == null) {
        if (other.b != null) {
          return false;
        }
      } else if (!b.equals(other.b)) {
        return false;
      }
      if (c == null) {
        if (other.c != null) {
          return false;
        }
      } else if (!c.equals(other.c)) {
        return false;
      }
      return true;
    }
  }
}
