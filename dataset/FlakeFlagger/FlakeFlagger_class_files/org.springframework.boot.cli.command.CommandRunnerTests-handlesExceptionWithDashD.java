/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.cli.command;

import java.util.EnumSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.cli.command.core.HelpCommand;
import org.springframework.boot.cli.command.core.HintCommand;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link CommandRunner}.
 *
 * @author Phillip Webb
 * @author Dave Syer
 */
public class CommandRunnerTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	private CommandRunner commandRunner;

	@Mock
	private Command regularCommand;

	@Mock
	private Command shellCommand;

	@Mock
	private Command anotherCommand;

	private final Set<Call> calls = EnumSet.noneOf(Call.class);

	private ClassLoader loader;

	@Before
	public void setup() {
		this.loader = Thread.currentThread().getContextClassLoader();
		MockitoAnnotations.initMocks(this);
		this.commandRunner = new CommandRunner("spring") {

			@Override
			protected void showUsage() {
				CommandRunnerTests.this.calls.add(Call.SHOW_USAGE);
				super.showUsage();
			};

			@Override
			protected boolean errorMessage(String message) {
				CommandRunnerTests.this.calls.add(Call.ERROR_MESSAGE);
				return super.errorMessage(message);
			}

			@Override
			protected void printStackTrace(Exception ex) {
				CommandRunnerTests.this.calls.add(Call.PRINT_STACK_TRACE);
				super.printStackTrace(ex);
			}
		};
		given(this.anotherCommand.getName()).willReturn("another");
		given(this.regularCommand.getName()).willReturn("command");
		given(this.regularCommand.getDescription()).willReturn("A regular command");
		this.commandRunner.addCommand(this.regularCommand);
		this.commandRunner.addCommand(new HelpCommand(this.commandRunner));
		this.commandRunner.addCommand(new HintCommand(this.commandRunner));
	}

	@Test public void handlesExceptionWithDashD() throws Exception{willThrow(new RuntimeException()).given(this.regularCommand).run();int status=this.commandRunner.runAndHandleErrors("command","-d");assertEquals("true",System.getProperty("debug"));assertThat(status,equalTo(1));assertThat(this.calls,equalTo((Set<Call>)EnumSet.of(Call.ERROR_MESSAGE,Call.PRINT_STACK_TRACE)));}

	private static enum Call {
		SHOW_USAGE, ERROR_MESSAGE, PRINT_STACK_TRACE
	}
}
