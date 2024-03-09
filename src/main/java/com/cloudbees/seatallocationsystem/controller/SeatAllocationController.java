package com.cloudbees.seatallocationsystem.controller;

import com.cloudbees.seatallocationsystem.dto.request.SeatAllocationDTO;
import com.cloudbees.seatallocationsystem.model.SeatAllocationReceipt;
import com.cloudbees.seatallocationsystem.service.SeatAllocationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<List<SeatAllocationReceipt>> getSeatAllocationsBySection(@PathVariable String section){
        List<SeatAllocationReceipt> seatAllocations = seatAllocationService.getSeatAllocationsBySection(section);
        return seatAllocations.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(seatAllocations);
    }

    @DeleteMapping("/{receiptId}")
    public ResponseEntity<String> removeSeatAllocation(@PathVariable Long receiptId){
        try{
            seatAllocationService.removeSeatAllocation(receiptId);
        }catch (IllegalArgumentException exception){
            return ResponseEntity.unprocessableEntity().body(exception.getMessage());
        }
        return ResponseEntity.ok("User removed successfully");
    }

    @PutMapping("/{receiptId}")
    public ResponseEntity<String> updateSeat(@PathVariable Long receiptId, @RequestBody  SeatAllocationDTO seatAllocationDTO){
       Boolean updatedSeatAllocation= seatAllocationService.updateSeatNumber(receiptId,seatAllocationDTO);
        return updatedSeatAllocation? ResponseEntity.ok("User seat details updated successfully"):
                ResponseEntity.ok("User not allotted with seat");
    }
}
