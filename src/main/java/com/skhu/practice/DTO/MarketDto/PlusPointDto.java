package com.skhu.practice.DTO.MarketDto;


import com.skhu.practice.Entity.User;
import com.skhu.practice.Entity.market.How;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Data
@Builder
public class PlusPointDto {

    private Integer how;

    private Long money;
}
