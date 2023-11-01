package ru.practicum.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ViewStats {
    @NotBlank
    private String app;
    @NotBlank
    private String uri;
    @PositiveOrZero
    private long hits;
}
