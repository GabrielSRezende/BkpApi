package br.com.cenpros.bkpapi.controller;

import javax.servlet.annotation.MultipartConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.cenpros.bkpapi.storage.Disco;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {
	
	@Autowired
	private Disco disco;
	
	@PostMapping
	public void upload(@RequestParam MultipartFile arquivo) {
		disco.saveArquivo(arquivo);
	}

}
