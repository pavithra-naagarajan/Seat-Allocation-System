package com.cloudbees.seatallocationsystem.controller;

import com.cloudbees.seatallocationsystem.dto.request.SeatAllocationDTO;
import com.cloudbees.seatallocationsystem.model.SeatAllocationReceipt;
import com.cloudbees.seatallocationsystem.service.SeatAllocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/seat-allocation")
@RestController
public class SeatAllocationController {
    private final SeatAllocationService seatAllocationService;
    public SeatAllocationController(SeatAllocationService seatAllocationService){
        this.seatAllocationService=seatAllocationService;
    }


    @PostMapping
    public ResponseEntity<SeatAllocationReceipt> doGetBatchByBatchCurrActId(@RequestBody SeatAllocationDTO seatAllocation){
        SeatAllocationReceipt seatAllocationReceipt = seatAllocationService.saveSeatAllocation(seatAllocation);
        return ResponseEntity.ok(seatAllocationReceipt);
    }

    @GetMapping("/user/{receiptId}")
    public ResponseEntity<SeatAllocationReceipt> getReceiptById(@PathVariable Long receiptId){
        Optional<SeatAllocationReceipt> receipt = seatAllocationService.findSeatAllocationByReceiptId(receiptId);
        return receipt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{section}")
    public ResponseEntity<List<SeatAllocationReceipt>> getSeatAllocationBySection(@PathVariable String section){
        List<SeatAllocationReceipt> seatAllocations = seatAllocationService.seatAllocationsBySection(section);
        return seatAllocations.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(seatAllocations);
    }
}
