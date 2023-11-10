package ru.practicum.explorewithme.request.dto;

import ru.practicum.explorewithme.request.model.ParticipationRequest;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class RequestMapper {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static ParticipationRequestDto toDto(ParticipationRequest request) {
        Long id = request.getId();
        String created = request.getCreated().format(FORMATTER);
        Long event = request.getEvent().getId();
        Long requester = request.getRequester().getId();
        String status = String.valueOf(request.getStatus());

        return new ParticipationRequestDto(id, created, event, requester, status);
    }

    public static List<ParticipationRequestDto> toDto(List<ParticipationRequest> requests) {
        return requests.stream()
                .map(RequestMapper::toDto)
                .collect(Collectors.toList());
    }
}