package com.cloudbees.seatallocationsystem.service;

import com.cloudbees.seatallocationsystem.model.SeatAllocationReceipt;
import com.cloudbees.seatallocationsystem.testutil.SeatAllocationTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import static org.mockito.Mockito.when;


public class SeatAllocationServiceTest {
    @Mock
    private SeatAllocationService seatAllocationService;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }



    @Test
    void given_validReceiptId_when_findById_then_shouldReturnReceipt(){
        when(seatAllocationService.findSeatAllocationByReceiptId(1L)).thenReturn(Optional.of(SeatAllocationTestUtil.getSeatAllocationReceipt()));
        Optional<SeatAllocationReceipt> receipt = seatAllocationService.findSeatAllocationByReceiptId(1L);
        Assertions.assertNotEquals(Optional.empty(),receipt);
    }

    @Test
    void given_inValidUserId_when_findById_then_shouldReturnEmptyOptional() {
        seatAllocationService.saveSeatAllocation(SeatAllocationTestUtil.getSeatAllocationDTO());
        Optional<SeatAllocationReceipt> receipt = seatAllocationService.findSeatAllocationByReceiptId(0L);
        Assertions.assertEquals(Optional.empty(), receipt);
    }
    @Test
    void given_validUserId_when_updateSeatNumber_then_shouldUpdateSeatNumber() {
        SeatAllocationReceipt receipt = SeatAllocationTestUtil.getSeatAllocationReceipt();
        seatAllocationService.saveSeatAllocation(SeatAllocationTestUtil.getSeatAllocationDTO());
        seatAllocationService.updateSeatNumber(receipt.getId(), SeatAllocationTestUtil.getSeatAllocationDTO());
        String afterSection = receipt.getSection();
        Long afterSeatNumber = receipt.getSeatNumber();
        Assertions.assertEquals(2133, afterSeatNumber);
        Assertions.assertEquals("A", afterSection);
    }
    @Test
    void removeSeatAllocation_ExistingReceipt() {
        when(seatAllocationService.findSeatAllocationByReceiptId(1L)).thenReturn(Optional.of(SeatAllocationTestUtil.getSeatAllocationReceipt()));
        seatAllocationService.removeSeatAllocation(1L);
    }
}
