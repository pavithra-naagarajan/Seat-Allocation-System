package com.cloudbees.seatallocationsystem.service;


import com.cloudbees.seatallocationsystem.dto.request.SeatAllocationDTO;
import com.cloudbees.seatallocationsystem.model.SeatAllocationReceipt;

import java.util.List;
import java.util.Optional;

public interface SeatAllocationService {
    SeatAllocationReceipt saveSeatAllocation(SeatAllocationDTO seatAllocation);

    Optional<SeatAllocationReceipt> findSeatAllocationByReceiptId(Long receiptId);

    List<SeatAllocationReceipt> seatAllocationsBySection(String section);
}
