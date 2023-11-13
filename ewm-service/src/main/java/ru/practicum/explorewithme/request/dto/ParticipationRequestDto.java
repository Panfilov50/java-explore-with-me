package ru.practicum.explorewithme.request.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public final class ParticipationRequestDto {

    private Long id;
    private String created;
    private Long event;
    private Long requester;
    private String status;
}
