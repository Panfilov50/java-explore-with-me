package ru.practicum.explorewithme.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.explorewithme.category.exception.CategoryNotFoundException;
import ru.practicum.explorewithme.compilation.exception.CompilationNotFoundException;
import ru.practicum.explorewithme.event.exception.EventBadStateException;
import ru.practicum.explorewithme.event.exception.EventBadTimeException;
import ru.practicum.explorewithme.event.exception.EventIsNotPublishedException;
import ru.practicum.explorewithme.event.exception.EventNotFoundException;
import ru.practicum.explorewithme.location.exception.LocationNotFoundException;
import ru.practicum.explorewithme.location.exception.LocationsFieldsIsEmptyException;
import ru.practicum.explorewithme.request.exception.DuplicateRequestException;
import ru.practicum.explorewithme.request.exception.ParticipantLimitException;
import ru.practicum.explorewithme.request.exception.RequestOwnerException;
import ru.practicum.explorewithme.user.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        List<String> errors = new ArrayList<>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            errors.add(stackTraceElement + "\n");
        }
        log.info(errors + "MESSAGE: " + e.getMessage());

        return new ApiError(errors, e.getMessage(), e.getLocalizedMessage(),HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                LocalDateTime.now().format(FORMATTER));
    }

    @ExceptionHandler({FieldIsNotValidException.class, EventBadTimeException.class, MethodArgumentNotValidException.class, LocationsFieldsIsEmptyException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleRuntimeException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        List<String> errors = new ArrayList<>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            errors.add(stackTraceElement + "\n");
        }
        log.info(errors + "MESSAGE: " + e.getMessage());

        return new ApiError(errors, e.getMessage(), e.getLocalizedMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), LocalDateTime.now().format(FORMATTER));
    }

    @ExceptionHandler({EventBadStateException.class,
            ParticipantLimitException.class,
            DuplicateRequestException.class,
            RequestOwnerException.class,
            EventIsNotPublishedException.class,
            DataIntegrityViolationException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleEventBadStateException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        List<String> errors = new ArrayList<>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            errors.add(stackTraceElement + "\n");
        }

        return new ApiError(errors, e.getMessage(), e.getLocalizedMessage(),
                HttpStatus.CONFLICT.toString(), LocalDateTime.now().format(FORMATTER));
    }

    @ExceptionHandler({CategoryNotFoundException.class,
            CompilationNotFoundException.class,
            UserNotFoundException.class,
            EventNotFoundException.class,
            LocationNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleEventNotFoundException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        List<String> errors = new ArrayList<>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            errors.add(stackTraceElement + "\n");
        }

        return new ApiError(errors, e.getMessage(), e.getLocalizedMessage(),
                HttpStatus.NOT_FOUND.toString(), LocalDateTime.now().format(FORMATTER));
    }
}
