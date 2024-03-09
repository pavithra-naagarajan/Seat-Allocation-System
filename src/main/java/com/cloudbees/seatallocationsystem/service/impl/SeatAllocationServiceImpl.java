package com.cloudbees.seatallocationsystem.service.impl;

import com.cloudbees.seatallocationsystem.dto.request.SeatAllocationDTO;
import com.cloudbees.seatallocationsystem.model.SeatAllocationReceipt;
import com.cloudbees.seatallocationsystem.service.SeatAllocationService;
import com.cloudbees.seatallocationsystem.util.SeatAllocationServiceUtil;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class SeatAllocationServiceImpl implements SeatAllocationService {
    private Map<Long, SeatAllocationReceipt> seatAllocationReceiptMap = new HashMap<>();
    private Long id=1L;
    @Override
    public SeatAllocationReceipt saveSeatAllocation(SeatAllocationDTO seatAllocation) throws IllegalArgumentException{
        SeatAllocationReceipt seatAllocationReceipt = SeatAllocationServiceUtil.generateSeatAllocationReceipt(seatAllocation,id);
        if(Objects.nonNull(seatAllocationReceipt)){
            seatAllocationReceiptMap.put(id,seatAllocationReceipt);
            return seatAllocationReceiptMap.get(id++);
        }
        else{
            throw new IllegalArgumentException("User not registered already");
        }

    }

    @Override
    public Optional<SeatAllocationReceipt> findSeatAllocationByReceiptId(Long receiptId) {
        return Optional.ofNullable(seatAllocationReceiptMap.get(receiptId));
    }
    @Override
    public List<SeatAllocationReceipt> getSeatAllocationsBySection(String section){
        List<SeatAllocationReceipt> receipts = new ArrayList<>(seatAllocationReceiptMap.values());
        return receipts.stream().filter(receipt -> receipt.getSection().equals(section)).toList();
    }

    @Override
    public void removeSeatAllocation(Long receiptId) throws IllegalArgumentException{
           Optional<SeatAllocationReceipt> seatAllocation = findSeatAllocationByReceiptId(receiptId);
           if(seatAllocation.isPresent()){
               seatAllocationReceiptMap.remove(receiptId);
           }
           else{
               throw new IllegalArgumentException("User not allotted with seat");
           }
    }

    @Override
    public Boolean updateSeatNumber(Long receiptId, SeatAllocationDTO seatAllocationDTO) {
        Optional<SeatAllocationReceipt> seatAllocationReceipt = findSeatAllocationByReceiptId(receiptId);
        if(seatAllocationReceipt.isPresent()) {
            seatAllocationReceipt.get().setSeatNumber(seatAllocationDTO.getSeatNumber());
            seatAllocationReceipt.get().setSection(seatAllocationDTO.getSection());
            seatAllocationReceiptMap.put(seatAllocationReceipt.get().getId(), seatAllocationReceipt.get());
            return true;
        }
        return false;
    }
}
