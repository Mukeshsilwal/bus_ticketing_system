package com.Transaction.transaction.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/corn")
public class CornJobController {

    @GetMapping("/status")
    public String getStatus() {
        return "Corn Job is running";
    }
}
