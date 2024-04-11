package org.nakedprogrammer.javaattestationmicro;

import java.lang.instrument.Instrumentation;

public class Agent {

    public static Instrumentation instrument;

    public static void premain(String args, Instrumentation instrument){
        Agent.instrument = instrument;
    }

}
