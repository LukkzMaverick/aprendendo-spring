package com.lukkzmaverick.AprendendoSpring;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Demo {
	@GetMapping("/")
	public String sayHello(@RequestParam(value = "name", defaultValue = "World")String name) {
		return String.format("Hello %s", name);
	}
	
	@PostMapping("/post")
	public String sayHelloPost(@RequestBody Map<String, Object> payload) {
		return payload.get("name").toString();
	}
	
	@GetMapping("/meuTio")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public String meuTio(@RequestParam(value = "name", required = false)String name) {
		return "meu tio";
	}
	
	@GetMapping("/{dynamic}")
	public String dynamicSubPath(@PathVariable("dynamic") String name) {
		return String.format("Hello %s", name);
	}
}
