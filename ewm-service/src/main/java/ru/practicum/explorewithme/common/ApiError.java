package ru.practicum.explorewithme.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ApiError {
    private List<String> error;
    private String message;
    private String reason;
    private String status;
    private String timestamp;
}
