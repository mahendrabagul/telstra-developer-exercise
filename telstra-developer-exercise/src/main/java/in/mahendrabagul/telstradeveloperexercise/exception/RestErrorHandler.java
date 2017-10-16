package in.mahendrabagul.telstradeveloperexercise.exception;

/**
 * @author mahendra.bagul
 */
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestErrorHandler extends ResponseEntityExceptionHandler {
	/**
	 * I tried to handle "user not found in the system" exception I could not handle
	 * the exception when particular discount will not be applied to a product.
	 */
	@ExceptionHandler(Exception.class)
	protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "{\"Code\":\"ER001\",\"message\":\"This is generic exception from discount service\"}";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(HttpClientErrorException.class)
	protected ResponseEntity<Object> handleBadRequest(RuntimeException ex, WebRequest request) {
		String bodyOfResponse = "{\"Code\":\"ER002\",\"message\":\"User not found in the system\"}";
		return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
}
