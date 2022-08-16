/*
 Copyright (C) 2015 Electronic Arts Inc.  All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 1.  Redistributions of source code must retain the above copyright
     notice, this list of conditions and the following disclaimer.
 2.  Redistributions in binary form must reproduce the above copyright
     notice, this list of conditions and the following disclaimer in the
     documentation and/or other materials provided with the distribution.
 3.  Neither the name of Electronic Arts, Inc. ("EA") nor the names of
     its contributors may be used to endorse or promote products derived
     from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY ELECTRONIC ARTS AND ITS CONTRIBUTORS "AS IS" AND ANY
 EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL ELECTRONIC ARTS OR ITS CONTRIBUTORS BE LIABLE FOR ANY
 DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.ea.orbit.actors.test;

import com.ea.orbit.actors.OrbitStage;
import com.ea.orbit.actors.runtime.Execution;
import com.ea.orbit.actors.test.actors.ISomeActor;
import com.ea.orbit.actors.test.actors.ISomeMatch;
import com.ea.orbit.actors.test.actors.ISomePlayer;
import com.ea.orbit.actors.test.actors.SomeActor;
import com.ea.orbit.actors.test.actors.SomeMatch;
import com.ea.orbit.actors.test.actors.SomePlayer;
import com.ea.orbit.concurrent.Task;

import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Test nodes that do not contain the same collection of actors.
 */
public class AsymmetricalStagesTest extends ActorBaseTest
{


    private void setField(Object target, String name, Object value) throws IllegalAccessException, NoSuchFieldException
    {
        final Field f = target.getClass().getDeclaredField(name);
        f.setAccessible(true);
        f.set(target, value);
    }

    @Test public void waitForNodeTest() throws ExecutionException,InterruptedException,NoSuchFieldException,IllegalAccessException{OrbitStage stage1=createStage(SomeActor.class,SomePlayer.class);final Task<String> test=stage1.getReference(ISomeMatch.class,"100").getNodeId();OrbitStage stage2=createStage(SomeActor.class,SomeMatch.class);assertNotNull(test.join());}

}
