package org.nakedprogrammer.javaattestationmicro;

import org.negro.compiler.CompilationException;
import org.negro.compiler.InMemoryJavaCompiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.junit.runner.*;

import java.util.Map;

@Service
public class AttestationService {

    @Autowired
    InMemoryJavaCompiler compiler;

    public String attest(String classname, String sourceCode, String testSourceCode, int timelimit) throws Exception{
        compiler.addSource("env." + classname, "package " + "env; " +  sourceCode);
        compiler.addSource(classname + "Test", "import " + "env.*;" + testSourceCode);
        Map<String, Class<?>> compiled = compiler.compileAll();
        final Result[] res = new Result[1];
        Thread task = new Thread(() -> res[0] =
                JUnitCore.runClasses(compiled.get(classname + "Test") ));
        task.start(); task.join(timelimit); task.stop();
        if(res[0] == null)
            throw new CompilationException("Run time is over " + timelimit + " seconds");
        return res[0].getFailures().toString();
    }
}
