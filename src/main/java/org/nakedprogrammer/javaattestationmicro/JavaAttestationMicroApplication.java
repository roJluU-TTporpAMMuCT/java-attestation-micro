package org.nakedprogrammer.javaattestationmicro;

import org.negro.compiler.InMemoryJavaCompiler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaAttestationMicroApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaAttestationMicroApplication.class, args);
    }


    @Bean
    public InMemoryJavaCompiler inMemoryJavaCompiler(){
        return new InMemoryJavaCompiler(Agent.instrument);
    }

}
