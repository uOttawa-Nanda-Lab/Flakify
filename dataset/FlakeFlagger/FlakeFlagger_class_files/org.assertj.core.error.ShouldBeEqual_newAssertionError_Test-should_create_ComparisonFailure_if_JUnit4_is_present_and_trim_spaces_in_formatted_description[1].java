package org.assertj.core.error;
import java.io.File;
import java.lang.Thread;
import java.lang.Runnable;
import java.util.concurrent.*;
import java.sql.*;
import java.net.*;

public class ShouldBeEqual_newAssertionError_Test {
@Test public void should_create_ComparisonFailure_if_JUnit4_is_present_and_trim_spaces_in_formatted_description(){
  when(formatter.format(description)).thenReturn(formattedDescription);
  AssertionError error=factory.newAssertionError(description,new StandardRepresentation());
  assertEquals(ComparisonFailure.class,error.getClass());
  assertEquals("[Jedi] expected:<\"[Yoda]\"> but was:<\"[Luke]\">",error.getMessage());
}

}