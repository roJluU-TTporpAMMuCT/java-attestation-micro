package org.nakedprogrammer.javaattestationmicro;

import org.negro.compiler.CompilationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AttestationController {

    @Autowired
    private AttestationService attestationService;

    @PostMapping("")
    public String postQuest(@RequestBody Map<String, String> body) throws Exception {
        try {
            return attestationService.attest(body.get("classname"), body.get("sourceCode"),
                                             body.get("testSourceCode"), Integer.parseInt(body.get("timelimit")) );
        }catch(CompilationException e) {
            return e.getMessage();
        }
    }
}
