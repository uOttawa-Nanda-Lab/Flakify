package org.apache.dubbo.common.serialize.serialization;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class Hessian2SerializationTest {
@Test public void test_EnumArray() throws Exception {
  assertObjectArray(new AnimalEnum[]{AnimalEnum.bull,AnimalEnum.cat,AnimalEnum.dog,AnimalEnum.horse},AnimalEnum[].class);
}

}