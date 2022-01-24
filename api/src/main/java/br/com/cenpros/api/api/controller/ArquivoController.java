package br.com.cenpros.api.api.controller;

import br.com.cenpros.api.api.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import br.com.cenpros.api.api.storage.Disco;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.validation.Valid;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {

	@Autowired
	private Disco disco;

	@PostMapping
	public void upload(@RequestParam @RequestBody @Valid MultipartFile arquivo) {
		disco.saveArquivo(arquivo);

		//Mensagem telegram
		SendMessage sendMessage = new SendMessage();
		sendMessage.setText("Backup enviado com sucesso!");
		sendMessage.setParseMode(ParseMode.MARKDOWN);
		sendMessage.setChatId("-788892618");

		Bot bot = new Bot();
		
		try {
			bot.execute(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
}
