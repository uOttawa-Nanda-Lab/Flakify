package org.assertj.core.util;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ArrayWrapperList_size_Test {
@Test public void should_return_size_of_array(){
  ArrayWrapperList list=new ArrayWrapperList(array);
  assertEquals(array.length,list.size());
}

}