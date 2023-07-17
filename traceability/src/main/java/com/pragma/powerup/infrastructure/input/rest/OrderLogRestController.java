package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.OrderLogRequestDto;
import com.pragma.powerup.application.dto.response.*;
import com.pragma.powerup.application.handler.IOrderLogHandler;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/traceability")
@RequiredArgsConstructor
public class OrderLogRestController {

    private final IOrderLogHandler orderLogHandler;


    @Operation(summary = "Add a new order log")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order Log created", content = @Content),
            @ApiResponse(responseCode = "400", description = "Order Log validation failed BadRequest", content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<ResponseDto> saveOrderLog(@Valid @RequestBody OrderLogRequestDto orderLogRequestDto,
                                                    BindingResult bindingResult) {
        ResponseDto responseDto = new ResponseDto();

        if (bindingResult.hasErrors()) {
            return ValidationErrors(bindingResult, responseDto);
        }try {
            OrderLogResponseDto orderLogResponseDto = orderLogHandler.saveOrderLog(orderLogRequestDto);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderLogResponseDto);

        }catch (Exception ex) {
            responseDto.setError(true);
            responseDto.setMessage("No se pudo crear el log");
            responseDto.setData(ex);
            return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all client order Logs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All order logs listed",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AllOrderLogResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
    })
    @GetMapping("/allOrderLogs/{id}")
    public ResponseEntity<ResponseDto> getAllOrderLogsByClientId(@Valid @PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();

        try {
            List<AllOrderLogResponseDto> orderLogResponseDtoList = orderLogHandler.getAllOrderLogsByClient(id);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderLogResponseDtoList);

        } catch (NoDataFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("No se encontró el cliente");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @Operation(summary = "Get efficiency by order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order efficiency",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AllOrderLogResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
    })
    @GetMapping("/efficiency/{id}")
    public ResponseEntity<ResponseDto> getEfficiencyByOrderId(@Valid @PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();

        try {
            OrderDurationResponseDto orderDurationResponseDto = orderLogHandler.getOrderDuration(id);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderDurationResponseDto);

        } catch (NoDataFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("No se encontró el pedido");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @Operation(summary = "Get orders efficiency")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order efficiency",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AllOrderLogResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
    })
    @GetMapping("/orderEfficiency/{id}")
    public ResponseEntity<ResponseDto> getEfficiency(@Valid @PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<OrderDurationResponseDto> orderDurationResponseDto = orderLogHandler.getAllOrderLogsEfficiency(id);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(orderDurationResponseDto);

        } catch (NoDataFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("No se encontró información");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }


    @Operation(summary = "Get employees average by restaurant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order efficiency average by employees",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AllOrderLogResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "No data found",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ResponseDto.class)))),
    })
    @GetMapping("/ranking/{id}")
    public ResponseEntity<ResponseDto> getRanking(@Valid @PathVariable Long id) {
        ResponseDto responseDto = new ResponseDto();
        try {
            List<EmployeeOrderDurationResponseDto> employeeOrderDurationResponseDtos = orderLogHandler.getAllOrderLogsEmployees(id);
            responseDto.setError(false);
            responseDto.setMessage(null);
            responseDto.setData(employeeOrderDurationResponseDtos);

        } catch (NoDataFoundException ex) {
            responseDto.setError(true);
            responseDto.setMessage("No se encontró información");
            responseDto.setData(null);
            return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }



    private ResponseEntity<ResponseDto> ValidationErrors(BindingResult bindingResult, ResponseDto responseDto) {
        List<String> errors = bindingResult.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList());

        responseDto.setError(true);
        responseDto.setMessage("Error en las validaciones");
        responseDto.setData(errors);

        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }

}
