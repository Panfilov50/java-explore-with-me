package ru.practicum.explorewithme.user.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.practicum.explorewithme.event.dto.EventRequestStatusUpdateRequest;
import ru.practicum.explorewithme.event.dto.EventRequestStatusUpdateResult;
import ru.practicum.explorewithme.event.dto.EventShortDto;
import ru.practicum.explorewithme.request.dto.ParticipationRequestDto;
import ru.practicum.explorewithme.event.service.EventService;
import ru.practicum.explorewithme.request.service.ParticipationRequestService;
import ru.practicum.explorewithme.user.dto.UserDto;
import ru.practicum.explorewithme.user.service.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final EventService eventService;
    private final ParticipationRequestService requestService;

    @GetMapping("/admin/users")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers(@RequestParam(required = false) List<Long> ids,
                                  @Min(0) @RequestParam(defaultValue = "0") int from,
                                  @Min(1) @RequestParam(defaultValue = "10") int size) {
        return userService.get(ids, from, size);
    }

    @PostMapping("/admin/users")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@Valid @RequestBody UserDto user) {
        return userService.add(user);
    }

    @DeleteMapping("/admin/users/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserDto deleteUser(@PathVariable(name = "userId") long id) {
        return userService.delete(id);
    }

    @GetMapping("/users/{userId}/events")
    public List<EventShortDto> getEventsForCurrentUser(@PathVariable long userId,
                                                       @Min(0) @RequestParam(defaultValue = "0") int from,
                                                       @Min(1) @RequestParam(defaultValue = "10") int size) {
        return eventService.findEvents(userId, from, size);
    }



    @GetMapping("/users/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> getParticipationRequest(@PathVariable long userId,
                                                                 @PathVariable long eventId) {
        return eventService.getParticipationRequest(userId, eventId);
    }

    @PatchMapping("/users/{userId}/events/{eventId}/requests")
    public EventRequestStatusUpdateResult updateParticipationRequest(@PathVariable long userId,
                                                                     @PathVariable long eventId,
                                                                     @Valid @RequestBody EventRequestStatusUpdateRequest request) {
        return eventService.changeRequestsStatus(userId, eventId, request);
    }

    @GetMapping("/users/{userId}/requests")
    public List<ParticipationRequestDto> getRequests(@PathVariable long userId) {
        return requestService.getRequests(userId);
    }

    @PostMapping("/users/{userId}/requests")
    @ResponseStatus(HttpStatus.CREATED)
    public ParticipationRequestDto createRequest(@PathVariable long userId,
                                                 @RequestParam long eventId) {
        return requestService.createRequest(userId, eventId);
    }

    @PatchMapping("/users/{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto cancelRequest(@PathVariable long userId,
                                                 @PathVariable long requestId) {
        return requestService.cancelRequest(userId, requestId);
    }
}
