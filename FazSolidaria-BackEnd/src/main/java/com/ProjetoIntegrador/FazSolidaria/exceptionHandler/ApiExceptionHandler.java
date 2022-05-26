package com.ProjetoIntegrador.FazSolidaria.exceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.TransientPropertyValueException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ProjetoIntegrador.FazSolidaria.exception.NaoEncontradoException;
import com.ProjetoIntegrador.FazSolidaria.exception.NegocioException;
import com.fasterxml.jackson.databind.JsonMappingException.Reference;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	public static final String MSG_ERRO_GENERICA_USUARIO_FINAL = "Ocorreu um erro interno inesperado no sistema."
			+ "Tente novamente e se o problema persistir, entre em contato " + "com o administrador do sistema";

	@Autowired
	private MessageSource messageSource;

	// FUNCIONA - Tratamento de erro para quando busca ou exclui algo inexistente
	@ExceptionHandler(NaoEncontradoException.class)
	public ResponseEntity<?> handleEntidadeNaoEncontradaException(NaoEncontradoException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		TipoProblema tipoProblema = TipoProblema.RECURSO_NAO_ENCONTRADO;
		String detalhe = ex.getMessage();
		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe)
				.mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	// Funciona - tratamento para pessima requisição
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		TipoProblema tipoProblema = TipoProblema.ERRO_NEGOCIO;
		String detail = ex.getMessage();

		Problema problema = criaProblemaBuilder(status, tipoProblema, detail)
				.mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

//	@ExceptionHandler(EntidadeEmUsoException.class)
//	public ResponseEntity<?> handleEntidadeEmUsoException(EntidadeEmUsoException ex, WebRequest request) {
//		HttpStatus status = HttpStatus.CONFLICT;
//		TipoProblema tipoProblema = TipoProblema.ENTIDADE_EM_USO;
//		String detalhe = ex.getMessage();
//
//		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe)
//				.mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
//
//		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
//	}

	// FUNCIONA - Tratamento de erro quando os atributos estão escritos errado
	private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers, HttpStatus status,
			WebRequest request, BindingResult bindingResult) {
		TipoProblema tipoProblema = TipoProblema.DADOS_INVALIDOS;
		String detalhe = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

		List<Problema.Objeto> problemaObjetos = bindingResult.getAllErrors().stream().map(objetoErro -> {
			String mensagem = messageSource.getMessage(objetoErro, LocaleContextHolder.getLocale());
			String nome = objetoErro.getObjectName();

			if (objetoErro instanceof FieldError) {
				nome = ((FieldError) objetoErro).getField();
			}
			return Problema.Objeto.builder().nome(nome).mensagemUsuario(mensagem).build();
		}).collect(Collectors.toList());

		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe)
				.mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).objetos(problemaObjetos).build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	// FUNCIONA - Tratamento de erro para quando o corpo do objeto esta errado
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Throwable causaRaiz = NestedExceptionUtils.getRootCause(ex);
		if (causaRaiz instanceof InvalidFormatException) {
			return handleFormatoInvalido((InvalidFormatException) causaRaiz, headers, status, request);
		}

		TipoProblema tipoProblema = TipoProblema.MENSAGEM_CORPO_INCOMPREENSIVEL;
		String detalhe = "O corpo da requisição está inválido. Verifique o erro de sintaxe";

		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe)
				.mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	// FUNCIONA - Metodo para tratar erro no parâmetro de busca
	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		TipoProblema tipoProblema = TipoProblema.PARAMETRO_INVALIDO;
		String detalhe = String.format("O parametro correto é '%s', preencha corretamente", ex.getParameterName());
		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe)
				.mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();

		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	// FUNCIONA - Metodo para trata erros no endpoint(configuração necessaria no
	// application.properties)
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		TipoProblema tipoProblema = TipoProblema.RECURSO_NAO_ENCONTRADO;
		String detalhe = String.format("O recurso %s, que você tentou acessar, é inexistente.", ex.getRequestURL());
		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe).mensagemUsuario(detalhe).build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	/*-----------------------------------------------------------------------------------------------------*/

	// FUNCIONA - Construtor da classe Problema utilizando Builder(construtor)
	private Problema.ProblemaBuilder criaProblemaBuilder(HttpStatus status, TipoProblema tipoProblema,
			String detalhes) {
		return Problema.builder().dataHora(new Date()).status(status.value()).tipo(tipoProblema.getUri())
				.titulo(tipoProblema.getTitle()).detalhe(detalhes);
	}

	// FUNCIONA - Construção do corpo exceção do spring para o Json
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		if (body == null) {
			body = Problema.builder().dataHora(new Date()).titulo(status.getReasonPhrase()).status(status.value())
					.mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		} else if (body instanceof String) {
			body = Problema.builder().dataHora(new Date()).titulo((String) body).status(status.value())
					.mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private String joinPath(List<Reference> references) {
		return references.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
	}

	// FUNCIONA - Metodo para quando faz um post e recebe uma valor que não é
	// adequado ao tipo do objeto
	private ResponseEntity<Object> handleFormatoInvalido(InvalidFormatException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {
		String path = joinPath(ex.getPath());
		TipoProblema tipoProblema = TipoProblema.MENSAGEM_CORPO_INCOMPREENSIVEL;
		String detalhe = String.format(
				"A propriedade '%s' recebeu o valor '%s', que é um tipo inválido. Corrija e informe um valor compatível com o tipo '%s'",
				path, ex.getValue(), ex.getTargetType().getSimpleName());
		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe)
				.mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}

//	FUNCIONA - Este metodo faz com que o corpo personalizado apareca no postman para o post e put
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
	}

	// FUNCIONA - Tratamento para quando a relação com outra classe não existit
	@ExceptionHandler(ConstraintViolationException.class)
	private ResponseEntity<Object> handleConstraint(ConstraintViolationException ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		TipoProblema tipoProblema = TipoProblema.ERRO_DE_SISTEM;
		String detalhe = MSG_ERRO_GENERICA_USUARIO_FINAL
				+ String.format("" + ", -> Foreign Key Id '%s' não existe a relação", ex.getConstraintName());
		ex.printStackTrace();
		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe).build();
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	// FUNCIONA - Tratamento para quando o id foreign key é nulo
	@ExceptionHandler(TransientPropertyValueException.class)
	private ResponseEntity<Object> handleTransientProperty(TransientPropertyValueException ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		TipoProblema tipoProblema = TipoProblema.ERRO_DE_SISTEM;
		String detalhe = MSG_ERRO_GENERICA_USUARIO_FINAL
				+ String.format("" + ", -> Foreign Key Id '%s' não pode ser nulo", ex.getPropertyName());
		ex.printStackTrace();
		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe).build();
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

	// FUNCIONA - Corpo de resposta para o valor do tipo Id na hora de buscar
//	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		TipoProblema tipoProblema = TipoProblema.PARAMETRO_INVALIDO;
		String detalhe = String.format(
				"O parâmetro de URL '%s', "
						+ "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo '%s'.",
				ex.getValue(), ex.getName());
		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe)
				.mensagemUsuario(MSG_ERRO_GENERICA_USUARIO_FINAL).build();
		return handleExceptionInternal(ex, problema, headers, status, request);
	}

	// FUNCIONA - Método para tratar valor do id invalido na busca
	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request) {

		if (ex instanceof MethodArgumentTypeMismatchException) {

			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers, status, request);
		}
		return super.handleTypeMismatch(ex, headers, status, request);
	}

	//FUNCIONA - Método para tratar na relação entre tabelas a partir da foreign key
	@ExceptionHandler(ClassNotFoundException.class)
	private ResponseEntity<Object> handleClassNotFoundException(ClassNotFoundException ex, WebRequest request) {
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		TipoProblema tipoProblema = TipoProblema.ERRO_DE_SISTEM;
		String detalhe = MSG_ERRO_GENERICA_USUARIO_FINAL
				+ String.format("" + ", -> Foreign Key Id '%s' não pode ser nulo ou Id inexistente", ex.getCause());
		ex.printStackTrace();
		Problema problema = criaProblemaBuilder(status, tipoProblema, detalhe).build();
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request);
	}

}
