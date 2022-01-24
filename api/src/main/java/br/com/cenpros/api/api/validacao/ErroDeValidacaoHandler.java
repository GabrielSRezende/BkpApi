package br.com.cenpros.api.api.validacao;

import java.util.ArrayList;
import java.util.List;

import br.com.cenpros.api.api.Bot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiValidationException;


@RestControllerAdvice
public class ErroDeValidacaoHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({MaxUploadSizeExceededException.class})
	public void handle(MaxUploadSizeExceededException exception) {

		SendMessage sendMessage = new SendMessage();
		sendMessage.setText("Erro! " + exception);
		sendMessage.setParseMode(ParseMode.MARKDOWN);
		sendMessage.setChatId("-788892618");

		Bot bot = new Bot();

		try {
			bot.execute(sendMessage);
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler({RuntimeException.class})
	public void handle2(RuntimeException runtimeException) {

		SendMessage sendMessage = new SendMessage();
		sendMessage.setText("Erro! " + runtimeException);
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
