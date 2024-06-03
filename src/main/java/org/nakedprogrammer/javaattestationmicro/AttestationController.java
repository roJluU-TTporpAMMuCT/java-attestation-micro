package org.nakedprogrammer.javaattestationmicro;

import org.junit.runner.notification.Failure;
import org.negro.compiler.CompilationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class AttestationController {

    @Autowired
    private AttestationService attestationService;

    @PostMapping("")
    public Map<String,String> postQuest(@RequestBody Map<String, String> body) throws Exception {
        Map<String,String> result;
        try {
            String lst = attestationService.attest(body.get("className"), body.get("sourceCode"),
                                             body.get("testSourceCode"), Integer.parseInt(body.get("timelimit")) );
            return Map.of("text", lst, "pass", String.valueOf(lst.equals("[]")));
        }catch(CompilationException e) {
            return Map.of("text", e.getMessage(), "pass", String.valueOf(false));
        }
    }
}
