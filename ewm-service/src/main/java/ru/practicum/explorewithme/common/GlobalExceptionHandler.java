package ru.practicum.explorewithme.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.explorewithme.category.exception.CategoryNotFoundException;
import ru.practicum.explorewithme.compilation.exception.CompilationNotFoundException;
import ru.practicum.explorewithme.event.exception.EventBadStateException;
import ru.practicum.explorewithme.event.exception.EventBadTimeException;
import ru.practicum.explorewithme.event.exception.EventIsNotPublishedException;
import ru.practicum.explorewithme.event.exception.EventNotFoundException;
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

    @ExceptionHandler({RuntimeException.class,
            EventBadTimeException.class,
            FieldIsNotValidException.class,
            EventBadStateException.class,
            EventNotFoundException.class,
            UserNotFoundException.class,
            CompilationNotFoundException.class,
            CategoryNotFoundException.class,
            ParticipantLimitException.class,
            DuplicateRequestException.class,
            RequestOwnerException.class,
            EventIsNotPublishedException.class,
            DataIntegrityViolationException.class

    })
    @ResponseStatus()
    public ApiError handleException(Exception e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        List<String> errors = new ArrayList<>();
        for (StackTraceElement stackTraceElement : stackTrace) {
            errors.add(stackTraceElement + "\n");
        }

        log.info(errors + "MESSAGE: " + e.getMessage());

        return new ApiError(errors, e.getMessage(), e.getLocalizedMessage(), LocalDateTime.now().format(FORMATTER));
    }

}
