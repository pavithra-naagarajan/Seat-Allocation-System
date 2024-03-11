package com.cloudbees.seatallocationsystem.controller;

import com.cloudbees.seatallocationsystem.dto.request.SeatAllocationDTO;
import com.cloudbees.seatallocationsystem.model.SeatAllocationReceipt;
import com.cloudbees.seatallocationsystem.service.SeatAllocationService;
import com.cloudbees.seatallocationsystem.testutil.SeatAllocationTestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@SpringBootTest
@AutoConfigureMockMvc
public class SeatAllocationControllerTest {
    private static final String BASE_URI = "/seat-allocation";


    @MockBean
    private SeatAllocationService seatAllocationService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void given_validReceiptId_when_getReceiptById_thenReturnSuccess() throws Exception {
        SeatAllocationReceipt seatAllocationReceipt = SeatAllocationTestUtil.getSeatAllocationReceipt();
        when(seatAllocationService.findSeatAllocationByReceiptId(1L)).thenReturn(Optional.of(seatAllocationReceipt));
        this.mockMvc.perform(get(BASE_URI + "/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void given_inValidReceiptId_when_getReceiptById_thenReturnNotFound() throws Exception {
        SeatAllocationReceipt seatAllocationReceipt = SeatAllocationTestUtil.getSeatAllocationReceipt();
        when(seatAllocationService.findSeatAllocationByReceiptId(1L)).thenReturn(Optional.of(seatAllocationReceipt));
        this.mockMvc.perform(get(BASE_URI + "/10")).andDo(print()).andExpect(status().isNotFound());
    }

    @Test
    void given_validReceiptId_when_updateSeat_thenReturnSuccess() throws Exception {
        String content = objectMapper.writeValueAsString(SeatAllocationTestUtil.getSeatAllocationDTO());
        when(seatAllocationService.findSeatAllocationByReceiptId(1L)).thenReturn(Optional.of(SeatAllocationTestUtil.getSeatAllocationReceipt()));
        when(seatAllocationService.updateSeatNumber(1L,SeatAllocationTestUtil.getSeatAllocationDTO())).thenReturn(true);
        this.mockMvc.perform(put(BASE_URI + "/1").content(content).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void given_inValidReceiptId_when_updateSeat_thenReturnMessage() throws Exception {
        String content = objectMapper.writeValueAsString(SeatAllocationTestUtil.getSeatAllocationDTO());
        when(seatAllocationService.findSeatAllocationByReceiptId(1L)).thenReturn(Optional.of(SeatAllocationTestUtil.getSeatAllocationReceipt()));
        when(seatAllocationService.updateSeatNumber(1L,SeatAllocationTestUtil.getSeatAllocationDTO())).thenReturn(false);
        this.mockMvc.perform(put(BASE_URI + "/1").content(content).contentType(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void given_validSeatAllocation_when_saveReceipt_thenReturnSuccess() throws Exception {
        when(seatAllocationService.saveSeatAllocation(Mockito.any())).thenReturn(SeatAllocationTestUtil.getSeatAllocationReceipt());
        this.mockMvc.perform(post(BASE_URI)
                        .content(objectMapper.writeValueAsString(SeatAllocationTestUtil.getSeatAllocationDTO()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void given_inValidSeatAllocation_when_saveReceipt_thenFailed() throws Exception {
        String errorMessage = "Some error message";
        when(seatAllocationService.saveSeatAllocation(Mockito.any()))
                .thenThrow(new IllegalArgumentException(errorMessage));
        this.mockMvc.perform(post(BASE_URI)
                        .content(objectMapper.writeValueAsString(SeatAllocationTestUtil.getSeatAllocationDTO()))
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnprocessableEntity());

    }




}
