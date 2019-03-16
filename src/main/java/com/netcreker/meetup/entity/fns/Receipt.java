package com.netcreker.meetup.entity.fns;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Receipt {
    private String fiscalDriveNumber;
    private String userInn;
    private Integer requestNumber;
    private Long fiscalSign;
    private String dateTime;
    private Integer ecashTotalSum;
    private List<Item> items = new ArrayList<>();
    private Integer cashTotalSum;
    private Integer totalSum;
    private String operator;
    private Integer nds10;
    private Long receiptCode;
    private Integer shiftNumber;
    private Integer nds18;
    private Long fiscalDocumentNumber;
    private Byte operationType;
    private String kktRegId;
    private String rawData;
    private Byte taxationType;
}
