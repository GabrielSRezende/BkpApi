package br.com.cenpros.api.api.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import br.com.cenpros.api.api.Bot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.validation.Valid;

@Component
public class Disco {
	
	@Value("/var/")
	private String raiz;

	@Value("Backups")
	private String diretorioArquivos;

	public void saveArquivo(MultipartFile foto) {
		this.salvar(this.diretorioArquivos, foto);
	}

	public void salvar(@RequestBody @Valid String diretorio, MultipartFile arquivo) {
		Path diretorioPath = Paths.get(this.raiz, diretorio);
		Path arquivoPath = diretorioPath.resolve(arquivo.getOriginalFilename());

		try {
			Files.createDirectories(diretorioPath);
			arquivo.transferTo(arquivoPath.toFile());
		} catch (IOException e) {
//			SendMessage sendMessage = new SendMessage();
//			sendMessage.setText("Erro no envio do backup!");
//			sendMessage.setParseMode(ParseMode.MARKDOWN);
//			sendMessage.setChatId("-788892618");
//
//			Bot bot = new Bot();
//
//			try {
//				bot.execute(sendMessage);
//			} catch (TelegramApiException e1) {
//				e1.printStackTrace();
//			}
			throw new RuntimeException("Problemas na tentativa de salvar arquivo.", e);
		}		
	}
}
