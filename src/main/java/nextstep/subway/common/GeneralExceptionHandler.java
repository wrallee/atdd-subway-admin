package nextstep.subway.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import nextstep.subway.line.exception.CannotRemoveException;
import nextstep.subway.line.exception.DuplicateLineException;
import nextstep.subway.line.exception.InvalidSectionException;
import nextstep.subway.line.exception.NoSuchLineException;

@ControllerAdvice
public class GeneralExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Void> handleNotFoundException(Exception e) {
        log.warn("핸들링 되지 않은 예외입니다.", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DuplicateLineException.class)
    public ResponseEntity<Void> duplicateException(Exception e) {
        log.debug("중복된 값이 입력되었습니다.", e);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(NoSuchLineException.class)
    public ResponseEntity<Void> noSuchResourceException(Exception e) {
        log.debug("존재하지 않는 값이 요청되었습니다.", e);
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InvalidSectionException.class)
    public ResponseEntity<Void> invalidSectionException(Exception e) {
        log.debug("등록 요청 된 구간에 오류가 있습니다.", e);
        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(CannotRemoveException.class)
    public ResponseEntity<Void> cannotRemoveException(Exception e) {
        log.debug("구간을 삭제하는데 실패했습니다.", e);
        return ResponseEntity.badRequest().build();
    }
}
